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
import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.represent.FilmsPerson;
import mos.edu.server.fancinema.service.PersonService;

@RestController
@RequestMapping(value = Constants.URI.PERSONS)
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Person>> getPersons(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
														 @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<Person> persons = personService.getPersons(page, size);
		if (persons.hasContent()) {
			final long lastPage = persons.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(persons.getSize(), persons.getNumber(), persons.getTotalElements(), lastPage);
			final PagedResources<Person> personsResource = 
					new PagedResources<>(persons.getContent(), metadata);
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				personsResource.add(linkTo(methodOn(PersonController.class).getPersons(prevPage, size)).withRel("prev"));
			personsResource.add(linkTo(methodOn(PersonController.class).getPersons(page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				personsResource.add(linkTo(methodOn(PersonController.class).getPersons(nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(personsResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.PERSON_BY_ID,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Person>> getPerson(@PathVariable(value = "id_person") int id) {
		if (id <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Person person = personService.getPerson(id);
		if (person != null) {
			final Resource<Person> personResource = new Resource<>(person);
			personResource.add(linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel());
			personResource.add(linkTo(methodOn(PersonController.class).getFilmsOfPerson(id)).withRel("films"));
			return new ResponseEntity<>(personResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.PERSON_FILMS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<FilmsPerson>> getFilmsOfPerson(@PathVariable(value = "id_person") int id) {
		if (id <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final FilmsPerson filmsPerson = personService.getFilmsOfPerson(id);
		if (filmsPerson != null) {
			final Resource<FilmsPerson> filmsPersonResource = new Resource<>(filmsPerson);
			filmsPersonResource.add(linkTo(methodOn(PersonController.class).getPerson(id)).withRel("self-person"));
			filmsPersonResource.add(linkTo(methodOn(PersonController.class).getFilmsOfPerson(id)).withSelfRel());
			return new ResponseEntity<>(filmsPersonResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
