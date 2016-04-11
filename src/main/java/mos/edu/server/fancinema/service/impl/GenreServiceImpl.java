package mos.edu.server.fancinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.represent.ShortFilm;
import mos.edu.server.fancinema.repository.GenreRepository;
import mos.edu.server.fancinema.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Genre> getGenres(int page, int size) {
		final Pageable pageRequest = new PageRequest(page, size);
		return genreRepository.findAll(pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ShortFilm> getFilmsOfGenre(byte id, int page, int size) {
		final Pageable pageRequest = new PageRequest(page, size);
		return genreRepository.findFilmsOfGenre(id, pageRequest);
	}

}
