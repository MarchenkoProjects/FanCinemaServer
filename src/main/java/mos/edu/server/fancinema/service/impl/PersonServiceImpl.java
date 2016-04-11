package mos.edu.server.fancinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.represent.FilmsPerson;
import mos.edu.server.fancinema.repository.PersonRepository;
import mos.edu.server.fancinema.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<Person> getPersons(int page, int size) {
		final Pageable pageRequest = new PageRequest(page, size);
		return personRepository.findAll(pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Person getPerson(int id) {
		return personRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public FilmsPerson getFilmsOfPerson(int id) {
		return personRepository.findFilmsOfPerson(id);
	}

}
