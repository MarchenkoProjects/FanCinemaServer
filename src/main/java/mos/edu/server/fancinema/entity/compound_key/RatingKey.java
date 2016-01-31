package mos.edu.server.fancinema.entity.compound_key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import mos.edu.server.fancinema.entity.FilmEntity;
import mos.edu.server.fancinema.entity.UserEntity;

@Embeddable
public class RatingKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private UserEntity user;
	
	@ManyToOne
	private FilmEntity film;
	
	public RatingKey() {}
	
}
