package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.represent.Creators;
import mos.edu.server.fancinema.entity.represent.FilmReviews;
import mos.edu.server.fancinema.entity.represent.Rating;
import mos.edu.server.fancinema.entity.represent.ShortFilm;

public interface FilmService {
	
	Page<ShortFilm> getFilms(int page, int size);
	Film getFilm(int id);
	
	Page<Genre> getFilmGenres(int id, int page, int size);
	Page<Country> getFilmCountries(int id, int page, int size);
	
	Creators getFilmCreators(int id);
	Page<Person> getFilmWriters(int id, int page, int size);
	Page<Person> getFilmProducers(int id, int page, int size);
	Page<Person> getFilmDirectors(int id, int page, int size);
	Page<Person> getFilmActors(int id, int page, int size);
	
	Rating getFilmRating(int id);
	Page<RatingFilm> getAllRating(int id, int page, int size);
	
	Page<FilmReviews> getAllReview(int id, int page, int size);
	
}
