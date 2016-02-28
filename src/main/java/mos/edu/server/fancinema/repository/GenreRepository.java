package mos.edu.server.fancinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.represent.ShortFilm;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Byte> {
	
	String FIND_FILMS_OF_GENRE = 
		"SELECT new ShortFilm(shortFilm, AVG(rating.rating), COUNT(rating.rating)) " + 
		"FROM ShortFilm shortFilm " + 
		"LEFT JOIN shortFilm.ratingFilms rating " + 
		"JOIN shortFilm.genres genres " + 
		"WHERE genres.idGenre = :id " +
		"GROUP BY shortFilm.idFilm";
	@Query(FIND_FILMS_OF_GENRE)
	Page<ShortFilm> findFilmsOfGenre(@Param("id") byte id, Pageable pageRequest);
	
}
