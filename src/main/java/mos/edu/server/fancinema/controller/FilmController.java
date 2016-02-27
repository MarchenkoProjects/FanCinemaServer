package mos.edu.server.fancinema.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
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
import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.represent.Creators;
import mos.edu.server.fancinema.entity.represent.FilmReview;
import mos.edu.server.fancinema.entity.represent.Rating;
import mos.edu.server.fancinema.entity.represent.ShortFilm;
import mos.edu.server.fancinema.service.FilmService;

@RestController
@RequestMapping(value = Constants.URI_FILMS)
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<ShortFilm>> getFilms(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
											 	 		  @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<ShortFilm> films = filmService.getFilms(page, size);
		if (films.hasContent()) {
			PageMetadata metadata = new PageMetadata(films.getSize(), films.getNumber(), films.getTotalElements(), films.getTotalPages());
			PagedResources<ShortFilm> filmResources = new PagedResources<>(films.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				filmResources.add(linkTo(methodOn(FilmController.class).getFilms(prev_page, size)).withRel("prev"));
			filmResources.add(linkTo(methodOn(FilmController.class).getFilms(page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = films.getTotalPages();
			if (next_page != last_page)
				filmResources.add(linkTo(methodOn(FilmController.class).getFilms(next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(filmResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_BY_ID,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Film>> getFilm(@PathVariable(value = "id_film") int id) {
		Film film = filmService.getFilm(id);
		if (film != null) {
			Resource<Film> filmResource = new Resource<>(film);
			filmResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withSelfRel());
			return new ResponseEntity<>(filmResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_GENRES,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Genre>> getFilmGenres(@PathVariable(value = "id_film") int id,
													  	   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													  	   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Genre> genres = filmService.getFilmGenres(id, page, size);
		if (genres != null && genres.hasContent()) {
			PageMetadata metadata = new PageMetadata(genres.getSize(), genres.getNumber(), genres.getTotalElements(), genres.getTotalPages());
			PagedResources<Genre> genreResources = new PagedResources<>(genres.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				genreResources.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, prev_page, size)).withRel("prev"));
			genreResources.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = genres.getTotalPages();
			if (next_page != last_page)
				genreResources.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(genreResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_COUNTRIES,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Country>> getFilmCountries(@PathVariable(value = "id_film") int id,
														   		@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
														   		@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Country> countries = filmService.getFilmCountries(id, page, size);
		if (countries != null && countries.hasContent()) {
			PageMetadata metadata = new PageMetadata(countries.getSize(), countries.getNumber(), countries.getTotalElements(), countries.getTotalPages());
			PagedResources<Country> countryResources = new PagedResources<>(countries.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				countryResources.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, prev_page, size)).withRel("prev"));
			countryResources.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = countries.getTotalPages();
			if (next_page != last_page)
				countryResources.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(countryResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_CREATORS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Creators>> getFilmCreators(@PathVariable(value = "id_film") int id) {
		Creators creators = filmService.getFilmCreators(id);
		if (creators != null) {
			Resource<Creators> creatorsResource = new Resource<>(creators);
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withSelfRel());
			return new ResponseEntity<>(creatorsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_WRITERS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmWriters(@PathVariable(value = "id_film") int id,
															 @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													   		 @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> writers = filmService.getFilmWriters(id, page, size);
		if (writers != null && writers.hasContent()) {
			PageMetadata metadata = new PageMetadata(writers.getSize(), writers.getNumber(), writers.getTotalElements(), writers.getTotalPages());
			PagedResources<Person> writersResources = new PagedResources<>(writers.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				writersResources.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, prev_page, size)).withRel("prev"));
			writersResources.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = writers.getTotalPages();
			if (next_page != last_page)
				writersResources.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(writersResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_PRODUCERS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmProducers(@PathVariable(value = "id_film") int id,
															   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
														   	   @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> producers = filmService.getFilmProducers(id, page, size);
		if (producers != null && producers.hasContent()) {
			PageMetadata metadata = new PageMetadata(producers.getSize(), producers.getNumber(), producers.getTotalElements(), producers.getTotalPages());
			PagedResources<Person> prodecersResources = new PagedResources<>(producers.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				prodecersResources.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, prev_page, size)).withRel("prev"));
			prodecersResources.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = producers.getTotalPages();
			if (next_page != last_page)
				prodecersResources.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(prodecersResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_DIRECTORS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmDirectors(@PathVariable(value = "id_film") int id,
															   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													   		   @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> directors = filmService.getFilmDirectors(id, page, size);
		if (directors != null && directors.hasContent()) {
			PageMetadata metadata = new PageMetadata(directors.getSize(), directors.getNumber(), directors.getTotalElements(), directors.getTotalPages());
			PagedResources<Person> directorsResources = new PagedResources<>(directors.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				directorsResources.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, prev_page, size)).withRel("prev"));
			directorsResources.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = directors.getTotalPages();
			if (next_page != last_page)
				directorsResources.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(directorsResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_ACTORS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmActors(@PathVariable(value = "id_film") int id,
														    @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
												   		    @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> actors = filmService.getFilmActors(id, page, size);
		if (actors != null && actors.hasContent()) {
			PageMetadata metadata = new PageMetadata(actors.getSize(), actors.getNumber(), actors.getTotalElements(), actors.getTotalPages());
			PagedResources<Person> actorsResources = new PagedResources<>(actors.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				actorsResources.add(linkTo(methodOn(FilmController.class).getFilmActors(id, prev_page, size)).withRel("prev"));
			actorsResources.add(linkTo(methodOn(FilmController.class).getFilmActors(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = actors.getTotalPages();
			if (next_page != last_page)
				actorsResources.add(linkTo(methodOn(FilmController.class).getFilmActors(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(actorsResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_RATING,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Rating>> getFilmRating(@PathVariable(value = "id_film") int id) {
		Rating filmRating = filmService.getFilmRating(id);
		if (filmRating != null) {
			Resource<Rating> actorsResource = new Resource<>(filmRating);
			actorsResource.add(linkTo(methodOn(FilmController.class).getFilmRating(id)).withSelfRel());
			return new ResponseEntity<>(actorsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_RATINGS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<RatingFilm>> getAllRating(@PathVariable(value = "id_film") int id,
															   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
															   @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<RatingFilm> ratings = filmService.getAllRating(id, page, size);
		if (ratings != null && ratings.hasContent()) {
			PageMetadata metadata = new PageMetadata(ratings.getSize(), ratings.getNumber(), ratings.getTotalElements(), ratings.getTotalPages());
			PagedResources<RatingFilm> ratingResources = new PagedResources<>(ratings.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				ratingResources.add(linkTo(methodOn(FilmController.class).getAllRating(id, prev_page, size)).withRel("prev"));
			ratingResources.add(linkTo(methodOn(FilmController.class).getAllRating(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = ratings.getTotalPages();
			if (next_page != last_page)
				ratingResources.add(linkTo(methodOn(FilmController.class).getAllRating(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(ratingResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI_FILM_REVIEWS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<FilmReview>> getAllReview(@PathVariable(value = "id_film") int id,
															    @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
															    @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<FilmReview> reviews = filmService.getAllReview(id, page, size);
		if (reviews != null && reviews.hasContent()) {
			PageMetadata metadata = new PageMetadata(reviews.getSize(), reviews.getNumber(), reviews.getTotalElements(), reviews.getTotalPages());
			PagedResources<FilmReview> ratingResources = new PagedResources<>(reviews.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				ratingResources.add(linkTo(methodOn(FilmController.class).getAllReview(id, prev_page, size)).withRel("prev"));
			ratingResources.add(linkTo(methodOn(FilmController.class).getAllReview(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = reviews.getTotalPages();
			if (next_page != last_page)
				ratingResources.add(linkTo(methodOn(FilmController.class).getAllReview(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(ratingResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
