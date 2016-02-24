package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.represent.FilmsPerson;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	String FIND_FILMS_OF_PERSON = 
		"SELECT filmsPerson " + 
		"FROM FilmsPerson filmsPerson " +
		"WHERE filmsPerson.idPerson = :id";
	@Query(FIND_FILMS_OF_PERSON)
	FilmsPerson findFilmsOfPerson(@Param("id") int id);
	
}
