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
@RequestMapping(value = Constants.URI.GENRES)
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Genre>> getGenres(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			 										   @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<Genre> genres = genreService.getGenres(page, size);
		if (genres.hasContent()) {
			final long lastPage = genres.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(genres.getSize(), genres.getNumber(), genres.getTotalElements(), lastPage);
			final PagedResources<Genre> genreResources = 
					new PagedResources<>(genres.getContent(), metadata);
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				genreResources.add(linkTo(methodOn(GenreController.class).getGenres(prevPage, size)).withRel("prev"));
			genreResources.add(linkTo(methodOn(GenreController.class).getGenres(page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				genreResources.add(linkTo(methodOn(GenreController.class).getGenres(nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(genreResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.GENRE_FILMS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<ShortFilm>> getFilmsOfGenre(@PathVariable(value = "id_genre") byte id,
																 @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
																 @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (id <= 0 || page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<ShortFilm> filmsGenre = genreService.getFilmsOfGenre(id, page, size);
		if (filmsGenre.hasContent()) {
			final long lastPage = filmsGenre.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(filmsGenre.getSize(), filmsGenre.getNumber(), filmsGenre.getTotalElements(), lastPage);
			final PagedResources<ShortFilm> filmsGenreResource = 
					new PagedResources<>(filmsGenre.getContent(), metadata);
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				filmsGenreResource.add(linkTo(methodOn(GenreController.class).getFilmsOfGenre(id, prevPage, size)).withRel("prev"));
			filmsGenreResource.add(linkTo(methodOn(GenreController.class).getFilmsOfGenre(id, page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				filmsGenreResource.add(linkTo(methodOn(GenreController.class).getFilmsOfGenre(id, nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(filmsGenreResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
