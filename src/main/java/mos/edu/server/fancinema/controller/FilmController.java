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
@RequestMapping(value = Constants.URI.FILMS)
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
			PagedResources<ShortFilm> filmsResource = new PagedResources<>(films.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				filmsResource.add(linkTo(methodOn(FilmController.class).getFilms(prev_page, size)).withRel("prev"));
			filmsResource.add(linkTo(methodOn(FilmController.class).getFilms(page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = films.getTotalPages();
			if (next_page < last_page)
				filmsResource.add(linkTo(methodOn(FilmController.class).getFilms(next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(filmsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_BY_ID,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Film>> getFilm(@PathVariable(value = "id_film") int id) {
		Film film = filmService.getFilm(id);
		if (film != null) {
			Resource<Film> filmResource = new Resource<>(film);
			filmResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withSelfRel());
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, 0, 10)).withRel("genres"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, 0, 10)).withRel("countries"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withRel("creators"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, 0, 20)).withRel("writers"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, 0, 20)).withRel("producers"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, 0, 20)).withRel("directors"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmActors(id, 0, 20)).withRel("actors"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmRating(id)).withRel("rating"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmRatings(id, 0, 30)).withRel("ratings"));
			filmResource.add(linkTo(methodOn(FilmController.class).getFilmReviews(id, 0, 30)).withRel("reviews"));
			return new ResponseEntity<>(filmResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_GENRES,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Genre>> getFilmGenres(@PathVariable(value = "id_film") int id,
													  	   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													  	   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Genre> genres = filmService.getFilmGenres(id, page, size);
		if (genres != null && genres.hasContent()) {
			PageMetadata metadata = new PageMetadata(genres.getSize(), genres.getNumber(), genres.getTotalElements(), genres.getTotalPages());
			PagedResources<Genre> genresResource = new PagedResources<>(genres.getContent(), metadata);
			genresResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				genresResource.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, prev_page, size)).withRel("prev"));
			genresResource.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = genres.getTotalPages();
			if (next_page < last_page)
				genresResource.add(linkTo(methodOn(FilmController.class).getFilmGenres(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(genresResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_COUNTRIES,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Country>> getFilmCountries(@PathVariable(value = "id_film") int id,
														   		@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
														   		@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Country> countries = filmService.getFilmCountries(id, page, size);
		if (countries != null && countries.hasContent()) {
			PageMetadata metadata = new PageMetadata(countries.getSize(), countries.getNumber(), countries.getTotalElements(), countries.getTotalPages());
			PagedResources<Country> countriesResource = new PagedResources<>(countries.getContent(), metadata);
			countriesResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				countriesResource.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, prev_page, size)).withRel("prev"));
			countriesResource.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = countries.getTotalPages();
			if (next_page < last_page)
				countriesResource.add(linkTo(methodOn(FilmController.class).getFilmCountries(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(countriesResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_CREATORS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Creators>> getFilmCreators(@PathVariable(value = "id_film") int id) {
		Creators creators = filmService.getFilmCreators(id);
		if (creators != null) {
			Resource<Creators> creatorsResource = new Resource<>(creators);
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withSelfRel());
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, 0, 20)).withRel("writers"));
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, 0, 20)).withRel("producers"));
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, 0, 20)).withRel("directors"));
			creatorsResource.add(linkTo(methodOn(FilmController.class).getFilmActors(id, 0, 20)).withRel("actors"));
			return new ResponseEntity<>(creatorsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_WRITERS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmWriters(@PathVariable(value = "id_film") int id,
															 @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													   		 @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> writers = filmService.getFilmWriters(id, page, size);
		if (writers != null && writers.hasContent()) {
			PageMetadata metadata = new PageMetadata(writers.getSize(), writers.getNumber(), writers.getTotalElements(), writers.getTotalPages());
			PagedResources<Person> writersResource = new PagedResources<>(writers.getContent(), metadata);
			writersResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			writersResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withRel("creators"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				writersResource.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, prev_page, size)).withRel("prev"));
			writersResource.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = writers.getTotalPages();
			if (next_page < last_page)
				writersResource.add(linkTo(methodOn(FilmController.class).getFilmWriters(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(writersResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_PRODUCERS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmProducers(@PathVariable(value = "id_film") int id,
															   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
														   	   @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> producers = filmService.getFilmProducers(id, page, size);
		if (producers != null && producers.hasContent()) {
			PageMetadata metadata = new PageMetadata(producers.getSize(), producers.getNumber(), producers.getTotalElements(), producers.getTotalPages());
			PagedResources<Person> producersResource = new PagedResources<>(producers.getContent(), metadata);
			producersResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			producersResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withRel("creators"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				producersResource.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, prev_page, size)).withRel("prev"));
			producersResource.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = producers.getTotalPages();
			if (next_page < last_page)
				producersResource.add(linkTo(methodOn(FilmController.class).getFilmProducers(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(producersResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_DIRECTORS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmDirectors(@PathVariable(value = "id_film") int id,
															   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													   		   @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> directors = filmService.getFilmDirectors(id, page, size);
		if (directors != null && directors.hasContent()) {
			PageMetadata metadata = new PageMetadata(directors.getSize(), directors.getNumber(), directors.getTotalElements(), directors.getTotalPages());
			PagedResources<Person> directorsResource = new PagedResources<>(directors.getContent(), metadata);
			directorsResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			directorsResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withRel("creators"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				directorsResource.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, prev_page, size)).withRel("prev"));
			directorsResource.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = directors.getTotalPages();
			if (next_page < last_page)
				directorsResource.add(linkTo(methodOn(FilmController.class).getFilmDirectors(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(directorsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_ACTORS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getFilmActors(@PathVariable(value = "id_film") int id,
														    @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
												   		    @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Person> actors = filmService.getFilmActors(id, page, size);
		if (actors != null && actors.hasContent()) {
			PageMetadata metadata = new PageMetadata(actors.getSize(), actors.getNumber(), actors.getTotalElements(), actors.getTotalPages());
			PagedResources<Person> actorsResource = new PagedResources<>(actors.getContent(), metadata);
			actorsResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			actorsResource.add(linkTo(methodOn(FilmController.class).getFilmCreators(id)).withRel("creators"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				actorsResource.add(linkTo(methodOn(FilmController.class).getFilmActors(id, prev_page, size)).withRel("prev"));
			actorsResource.add(linkTo(methodOn(FilmController.class).getFilmActors(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = actors.getTotalPages();
			if (next_page < last_page)
				actorsResource.add(linkTo(methodOn(FilmController.class).getFilmActors(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(actorsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_RATING,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Rating>> getFilmRating(@PathVariable(value = "id_film") int id) {
		Rating filmRating = filmService.getFilmRating(id);
		if (filmRating != null) {
			Resource<Rating> ratingResource = new Resource<>(filmRating);
			ratingResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			ratingResource.add(linkTo(methodOn(FilmController.class).getFilmRating(id)).withSelfRel());
			ratingResource.add(linkTo(methodOn(FilmController.class).getFilmRatings(id, 0, 30)).withRel("ratings"));
			return new ResponseEntity<>(ratingResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_RATINGS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<RatingFilm>> getFilmRatings(@PathVariable(value = "id_film") int id,
															   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
															   @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<RatingFilm> ratings = filmService.getAllRating(id, page, size);
		if (ratings != null && ratings.hasContent()) {
			PageMetadata metadata = new PageMetadata(ratings.getSize(), ratings.getNumber(), ratings.getTotalElements(), ratings.getTotalPages());
			PagedResources<RatingFilm> ratingsResource = new PagedResources<>(ratings.getContent(), metadata);
			ratingsResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			ratingsResource.add(linkTo(methodOn(FilmController.class).getFilmRating(id)).withRel("rating"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				ratingsResource.add(linkTo(methodOn(FilmController.class).getFilmRatings(id, prev_page, size)).withRel("prev"));
			ratingsResource.add(linkTo(methodOn(FilmController.class).getFilmRatings(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = ratings.getTotalPages();
			if (next_page < last_page)
				ratingsResource.add(linkTo(methodOn(FilmController.class).getFilmRatings(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(ratingsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.FILM_REVIEWS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<FilmReview>> getFilmReviews(@PathVariable(value = "id_film") int id,
															    @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
															    @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<FilmReview> reviews = filmService.getAllReview(id, page, size);
		if (reviews != null && reviews.hasContent()) {
			PageMetadata metadata = new PageMetadata(reviews.getSize(), reviews.getNumber(), reviews.getTotalElements(), reviews.getTotalPages());
			PagedResources<FilmReview> reviewsResource = new PagedResources<>(reviews.getContent(), metadata);
			reviewsResource.add(linkTo(methodOn(FilmController.class).getFilm(id)).withRel("self-film"));
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				reviewsResource.add(linkTo(methodOn(FilmController.class).getFilmReviews(id, prev_page, size)).withRel("prev"));
			reviewsResource.add(linkTo(methodOn(FilmController.class).getFilmReviews(id, page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = reviews.getTotalPages();
			if (next_page < last_page)
				reviewsResource.add(linkTo(methodOn(FilmController.class).getFilmReviews(id, next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(reviewsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
