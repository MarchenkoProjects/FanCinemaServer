package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.represent.ShortFilm;

public interface CountryService {

	Page<Country> getCountries(int page, int size);
	Page<ShortFilm> getFilmsOfCountry(short id, int page, int size);
	
}
