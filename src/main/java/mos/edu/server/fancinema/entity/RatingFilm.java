package mos.edu.server.fancinema.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.composite_key.RatingFilmKey;

@Entity
@Table(name = Constants.TABLE.RATING_FILM)
@JsonInclude(Include.NON_EMPTY)
public class RatingFilm implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String JOIN_COLUMN_USER = "user_id";
	public static final String JOIN_COLUMN_FILM = "film_id";
	public static final String COLUMN_RATING = "rating";
	
	@JsonIgnore
	@EmbeddedId
	private RatingFilmKey ratingFilmKey;
	
	@ManyToOne
	@JoinColumn(name = JOIN_COLUMN_USER, insertable = false, updatable = false)
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = JOIN_COLUMN_FILM, insertable = false, updatable = false)
	private Film film;

	@Column(name = COLUMN_RATING, nullable = false, columnDefinition = "TINYINT(2) UNSIGNED")
	private byte rating;
	
	protected RatingFilm() {}
	
	public RatingFilm(RatingFilmKey ratingFilmKey, byte rating) {
		this.ratingFilmKey = ratingFilmKey;
		this.rating = rating;
	}

	public RatingFilm(byte rating) {
		this.rating = rating;
	}
	
	public RatingFilmKey getRatingFilmKey() {
		return ratingFilmKey;
	}

	public void setRatingFilmKey(RatingFilmKey ratingFilmKey) {
		this.ratingFilmKey = ratingFilmKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + this.ratingFilmKey.hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		RatingFilm ratingFilm = (RatingFilm) obj;
		if (!this.ratingFilmKey.equals(ratingFilm.ratingFilmKey)) return false;
		
		return true;
	}
	
}
