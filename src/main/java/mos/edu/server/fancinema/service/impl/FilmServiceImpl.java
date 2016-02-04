package mos.edu.server.fancinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.FilmEntity;
import mos.edu.server.fancinema.repository.FilmRepository;
import mos.edu.server.fancinema.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<FilmEntity> getFilms(int offset, int limit) {
		Pageable pageRequest = new PageRequest(offset, limit);
		Page<FilmEntity> page = filmRepository.findAll(pageRequest);
		return page.getContent();
	}

	@Override
	@Transactional(readOnly = true)
	public FilmEntity getFilm(int id) {
		
		if (filmRepository.exists(id))
			return filmRepository.findOne(id);
		
		return null;
	}

}
