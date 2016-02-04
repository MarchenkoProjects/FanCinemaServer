package mos.edu.server.fancinema.service;

import java.util.List;

import mos.edu.server.fancinema.entity.FilmEntity;

public interface FilmService {
	
	List<FilmEntity> getFilms(int offset, int limit);
	FilmEntity getFilm(int id);
	
}
