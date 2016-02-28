package mos.edu.server.fancinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.represent.ShortFilm;
import mos.edu.server.fancinema.repository.CountryRepository;
import mos.edu.server.fancinema.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Country> getCountries(int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return countryRepository.findAll(pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ShortFilm> getFilmsOfCountry(short id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return countryRepository.findFilmsOfCountry(id, pageRequest);
	}

}
