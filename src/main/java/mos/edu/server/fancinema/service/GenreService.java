package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.represent.ShortFilm;

public interface GenreService {

	Page<Genre> getGenres(int page, int size);
	Page<ShortFilm> getFilmsOfGenre(byte id, int page, int size);
	
}
