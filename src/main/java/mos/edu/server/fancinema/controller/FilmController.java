package mos.edu.server.fancinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.FilmEntity;
import mos.edu.server.fancinema.service.FilmService;

@RestController
@RequestMapping(value = Constants.URI_FILMS)
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<List<FilmEntity>> getFilms(@RequestParam(value = "offset") int offset, 
												 @RequestParam(value = "limit") int limit) {
		if (offset >= 0 && limit > 0) {
			List<FilmEntity> films = filmService.getFilms(offset, limit);
			if (films != null && films.size() > 0)
				return new ResponseEntity<>(films, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{id_film}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<FilmEntity> getFilm(@PathVariable(value = "id_film") int id) {
		FilmEntity film = filmService.getFilm(id);
		if (film != null)
			return new ResponseEntity<>(film, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
