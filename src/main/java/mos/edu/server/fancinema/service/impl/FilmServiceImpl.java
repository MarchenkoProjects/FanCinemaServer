package mos.edu.server.fancinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.represent.Creators;
import mos.edu.server.fancinema.entity.represent.FilmReview;
import mos.edu.server.fancinema.entity.represent.Rating;
import mos.edu.server.fancinema.entity.represent.ShortFilm;
import mos.edu.server.fancinema.repository.FilmRepository;
import mos.edu.server.fancinema.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<ShortFilm> getFilms(int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findAllFilms(pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Film getFilm(int id) {
		return filmRepository.findFilmById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Genre> getFilmGenres(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findFilmGenres(id, pageRequest);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Country> getFilmCountries(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findFilmCountries(id, pageRequest);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Creators getFilmCreators(int id) {
		return filmRepository.findFilmCreators(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Person> getFilmWriters(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findFilmWriters(id, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Person> getFilmProducers(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findFilmProducers(id, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Person> getFilmDirectors(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findFilmDirectors(id, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Person> getFilmActors(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findFilmActors(id, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Rating getFilmRating(int id) {
		return filmRepository.findFilmRating(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RatingFilm> getAllRating(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findAllRating(id, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FilmReview> getAllReview(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return filmRepository.findAllReview(id, pageRequest);
	}

}
