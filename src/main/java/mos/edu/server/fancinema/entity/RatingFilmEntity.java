package mos.edu.server.fancinema.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.composite_key.RatingFilmKey;

@Entity
@Table(name = Constants.TABLE_RATING_FILM)
public class RatingFilmEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COLUMN_RATING = "rating";
	
	@EmbeddedId
	private RatingFilmKey ratingFilmKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private FilmEntity film;
	
	@Column(name = COLUMN_RATING, nullable = false, columnDefinition = "TINYINT(2) UNSIGNED")
	private byte rating;
	
	protected RatingFilmEntity() {}
	
	public RatingFilmEntity(int idUser, int idFilm, byte rating) {
		this.ratingFilmKey = new RatingFilmKey(idUser, idFilm);
		this.rating = rating;
	}

	public RatingFilmEntity(RatingFilmKey ratingFilmKey, byte rating) {
		this.ratingFilmKey = ratingFilmKey;
		this.rating = rating;
	}
	
	public RatingFilmKey getRatingFilmKey() {
		return ratingFilmKey;
	}

	public void setRatingFilmKey(RatingFilmKey ratingFilmKey) {
		this.ratingFilmKey = ratingFilmKey;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public FilmEntity getFilm() {
		return film;
	}

	public void setFilm(FilmEntity film) {
		this.film = film;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}
	
}
