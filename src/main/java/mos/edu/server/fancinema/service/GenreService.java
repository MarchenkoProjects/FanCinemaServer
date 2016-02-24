package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Genre;

public interface GenreService {

	Page<Genre> getGenres(int page, int size);
	
}
