package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.represent.FilmsPerson;

public interface PersonService {
	
	Page<Person> getPersons(int page, int size);
	Person getPerson(int id);
	
	FilmsPerson getFilmsOfPerson(int id);
	
}
