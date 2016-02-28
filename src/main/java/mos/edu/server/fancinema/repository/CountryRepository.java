package mos.edu.server.fancinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.represent.ShortFilm;

@Repository
public interface CountryRepository extends JpaRepository<Country, Short> {
	
	String FIND_FILMS_OF_COUNTRY = 
		"SELECT new ShortFilm(shortFilm, AVG(rating.rating), COUNT(rating.rating)) " + 
		"FROM ShortFilm shortFilm " + 
		"LEFT JOIN shortFilm.ratingFilms rating " + 
		"JOIN shortFilm.countries countries " + 
		"WHERE countries.idCountry = :id " +
		"GROUP BY shortFilm.idFilm";
	@Query(FIND_FILMS_OF_COUNTRY)
	Page<ShortFilm> findFilmsOfCountry(@Param("id") short id, Pageable pageRequest);
	
}
