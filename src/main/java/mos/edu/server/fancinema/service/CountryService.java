package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Country;

public interface CountryService {

	Page<Country> getCountries(int page, int size);
	
}
