package mos.edu.server.fancinema.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.represent.ShortFilm;
import mos.edu.server.fancinema.service.GenreService;

@RestController
@RequestMapping(value = Constants.URI_GENRES)
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Genre>> getGenres(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			 										   @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Genre> genres = genreService.getGenres(page, size);
		if (genres.hasContent()) {
			PageMetadata metadata = new PageMetadata(genres.getSize(), genres.getNumber(), genres.getTotalElements(), genres.getTotalPages());
			PagedResources<Genre> genreResources = new PagedResources<>(genres.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				genreResources.add(linkTo(methodOn(GenreController.class).getGenres(prev_page, size)).withRel("prev"));
			genreResources.add(linkTo(methodOn(GenreController.class).getGenres(page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = genres.getTotalPages();
			if (next_page < last_page)
				genreResources.add(linkTo(methodOn(GenreController.class).getGenres(next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(genreResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_GENRE_FILMS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<ShortFilm>> getFilmsOfGenre(@PathVariable(value = "id_genre") byte id,
																 @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
																 @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		Page<ShortFilm> filmsGenre = genreService.getFilmsOfGenre(id, page, size);
		if (filmsGenre.hasContent()) {
			PageMetadata metadata = new PageMetadata(filmsGenre.getSize(), filmsGenre.getNumber(), filmsGenre.getTotalElements(), filmsGenre.getTotalPages());
			PagedResources<ShortFilm> filmsGenreResource = new PagedResources<>(filmsGenre.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				filmsGenreResource.add(linkTo(methodOn(GenreController.class).getFilmsOfGenre(id, prev_page, size)).withRel("prev"));
			filmsGenreResource.add(linkTo(methodOn(GenreController.class).getFilmsOfGenre(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = filmsGenre.getTotalPages();
			if (next_page < last_page)
				filmsGenreResource.add(linkTo(methodOn(GenreController.class).getFilmsOfGenre(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(filmsGenreResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
