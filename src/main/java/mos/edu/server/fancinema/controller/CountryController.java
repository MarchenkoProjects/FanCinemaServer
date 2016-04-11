package mos.edu.server.fancinema.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
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
import mos.edu.server.fancinema.entity.represent.ShortFilm;
import mos.edu.server.fancinema.service.CountryService;

@RestController
@RequestMapping(value = Constants.URI.COUNTRIES)
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Country>> getCountries(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			 										        @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<Country> countries = countryService.getCountries(page, size);
		if (countries.hasContent()) {
			final long lastPage = countries.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(countries.getSize(), countries.getNumber(), countries.getTotalElements(), lastPage);
			final PagedResources<Country> countryResources = 
					new PagedResources<>(countries.getContent(), metadata);
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				countryResources.add(linkTo(methodOn(CountryController.class).getCountries(prevPage, size)).withRel("prev"));
			countryResources.add(linkTo(methodOn(CountryController.class).getCountries(page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				countryResources.add(linkTo(methodOn(CountryController.class).getCountries(nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(countryResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.COUNTRY_FILMS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<ShortFilm>> getFilmsOfCountry(@PathVariable(value = "id_country") short id,
															 	   @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
															 	   @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (id <= 0 || page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<ShortFilm> filmsCountry = countryService.getFilmsOfCountry(id, page, size);
		if (filmsCountry.hasContent()) {
			final long lastPage = filmsCountry.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(filmsCountry.getSize(), filmsCountry.getNumber(), filmsCountry.getTotalElements(), lastPage);
			final PagedResources<ShortFilm> filmsCountryResource = 
					new PagedResources<>(filmsCountry.getContent(), metadata);
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				filmsCountryResource.add(linkTo(methodOn(CountryController.class).getFilmsOfCountry(id, prevPage, size)).withRel("prev"));
			filmsCountryResource.add(linkTo(methodOn(CountryController.class).getFilmsOfCountry(id, page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				filmsCountryResource.add(linkTo(methodOn(CountryController.class).getFilmsOfCountry(id, nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(filmsCountryResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
