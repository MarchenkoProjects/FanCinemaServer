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
import mos.edu.server.fancinema.entity.composite_key.ReviewKey;

@Entity
@Table(name = Constants.TABLE_REVIEWS)
public class ReviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COLUMN_REVIEW = "review";
	
	@EmbeddedId
	private ReviewKey reviewKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private FilmEntity film;
	
	@Column(name = COLUMN_REVIEW, nullable = false, columnDefinition = "TEXT")
	private String review;
	
	protected ReviewEntity() {}
	
	public ReviewEntity(int idUser, int idFilm, String review) {
		this.reviewKey = new ReviewKey(idUser, idFilm);
		this.review = review;
	}

	public ReviewEntity(ReviewKey reviewKey, String review) {
		super();
		this.reviewKey = reviewKey;
		this.review = review;
	}

	public ReviewKey getReviewKey() {
		return reviewKey;
	}

	public void setReviewKey(ReviewKey reviewKey) {
		this.reviewKey = reviewKey;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
}
