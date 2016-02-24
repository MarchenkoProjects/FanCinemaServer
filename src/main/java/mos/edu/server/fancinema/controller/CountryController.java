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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.service.CountryService;

@RestController
@RequestMapping(value = Constants.URI_COUNTRIES)
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<Country>> getCountries(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
			 										        @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Page<Country> countries = countryService.getCountries(page, size);
		if (countries.hasContent()) {
			PageMetadata metadata = new PageMetadata(countries.getSize(), countries.getNumber(), countries.getTotalElements(), countries.getTotalPages());
			PagedResources<Country> countryResources = new PagedResources<>(countries.getContent(), metadata);
			
			int prev_page = page - 1;
			if (prev_page >= 0)
				countryResources.add(linkTo(methodOn(CountryController.class).getCountries(prev_page, size)).withRel("prev"));
			countryResources.add(linkTo(methodOn(CountryController.class).getCountries(page, size)).withSelfRel());
			int next_page = page + 1;
			long last_page = countries.getTotalPages();
			if (next_page != last_page)
				countryResources.add(linkTo(methodOn(CountryController.class).getCountries(next_page, size)).withRel("next"));
			
			return new ResponseEntity<>(countryResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
