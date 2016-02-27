package mos.edu.server.fancinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.represent.Creators;
import mos.edu.server.fancinema.entity.represent.FilmReview;
import mos.edu.server.fancinema.entity.represent.Rating;
import mos.edu.server.fancinema.entity.represent.ShortFilm;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
	
	String FIND_FILM_BY_ID = 
		"SELECT new Film(film, AVG(rating.rating), COUNT(rating.rating)) " +
		"FROM Film film " +
		"LEFT JOIN film.ratingFilms rating " +
		"WHERE film.idFilm = :id " + 
		"GROUP BY film.idFilm";
	@Query(FIND_FILM_BY_ID)
	Film findFilmById(@Param("id") int id);
	
	String FIND_ALL_FILMS = 
		"SELECT new ShortFilm(shortFilm, AVG(rating.rating), COUNT(rating.rating)) " + 
		"FROM ShortFilm shortFilm " +
		"LEFT JOIN shortFilm.ratingFilms rating " + 
		"GROUP BY shortFilm.idFilm";
	@Query(FIND_ALL_FILMS)
	Page<ShortFilm> findAllFilms(Pageable pageRequest);
	
	String FIND_FILM_GENRES = 
		"SELECT genres " + 
		"FROM Film film " + 
		"JOIN film.genres genres " + 
		"WHERE film.idFilm = :id";
	@Query(FIND_FILM_GENRES)
	Page<Genre> findFilmGenres(@Param("id") int id, Pageable pageRequest);
	
	String FIND_FILM_COUNTRIES = 
		"SELECT countries " + 
		"FROM Film film " + 
		"JOIN film.countries countries " + 
		"WHERE film.idFilm = :id";
	@Query(FIND_FILM_COUNTRIES)
	Page<Country> findFilmCountries(@Param("id") int id, Pageable pageRequest);
	
	String FIND_FILM_CREATORS = 
		"SELECT creators " + 
		"FROM Creators creators " +  
		"WHERE creators.idFilm = :id";
	@Query(FIND_FILM_CREATORS)
	Creators findFilmCreators(@Param("id") int id);
	
	String FIND_FILM_WRITERS = 
		"SELECT writers " + 
		"FROM Film film " + 
		"JOIN film.writers writers " + 
		"WHERE film.idFilm = :id";
	@Query(FIND_FILM_WRITERS)
	Page<Person> findFilmWriters(@Param("id") int id, Pageable pageRequest);
	
	String FIND_FILM_PRODUCERS = 
		"SELECT producers " + 
		"FROM Film film " + 
		"JOIN film.producers producers " + 
		"WHERE film.idFilm = :id";
	@Query(FIND_FILM_PRODUCERS)
	Page<Person> findFilmProducers(@Param("id") int id, Pageable pageRequest);
	
	String FIND_FILM_DIRECTORS = 
		"SELECT directors " + 
		"FROM Film film " + 
		"JOIN film.directors directors " + 
		"WHERE film.idFilm = :id";
	@Query(FIND_FILM_DIRECTORS)
	Page<Person> findFilmDirectors(@Param("id") int id, Pageable pageRequest);
	
	String FIND_FILM_ACTORS = 
		"SELECT actors " + 
		"FROM Film film " + 
		"JOIN film.actors actors " + 
		"WHERE film.idFilm = :id";
	@Query(FIND_FILM_ACTORS)
	Page<Person> findFilmActors(@Param("id") int id, Pageable pageRequest);
	
	String FIND_FILM_RATING = 
		"SELECT new Rating(AVG(ratingFilm.rating), COUNT(ratingFilm.rating)) " + 
		"FROM RatingFilm ratingFilm " + 
		"WHERE ratingFilm.ratingFilmKey.idFilm = :id " + 
		"GROUP BY ratingFilm.ratingFilmKey.idFilm";
	@Query(FIND_FILM_RATING)
	Rating findFilmRating(@Param("id") int id);
	
	String FIND_ALL_RATING = 
		"SELECT ratingFilm " + 
		"FROM RatingFilm ratingFilm " + 
		"WHERE ratingFilm.ratingFilmKey.idFilm = :id";
	@Query(FIND_ALL_RATING)
	Page<RatingFilm> findAllRating(@Param("id") int id, Pageable pageRequest);
	
	String FIND_ALL_REVIEWS = 
			"SELECT review " + 
			"FROM FilmReview review " + 
			"WHERE review.reviewKey.idFilm = :id";
	@Query(FIND_ALL_REVIEWS)
	Page<FilmReview> findAllReview(@Param("id") int id, Pageable pageRequest);
	
}
