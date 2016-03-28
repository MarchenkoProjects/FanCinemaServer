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
@Table(name = Constants.TABLE.REVIEWS)
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COLUMN_HEADER = "header";
	public static final String COLUMN_REVIEW = "review";
	
	@JsonIgnore
	@EmbeddedId
	private ReviewKey reviewKey;
	
	@Column(name = COLUMN_HEADER, length = 50, nullable = false)
	private String header;
	
	@Column(name = COLUMN_REVIEW, nullable = false, columnDefinition = "TEXT")
	private String review;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private Film film;
	
	protected Review() {}

	public ReviewKey getReviewKey() {
		return reviewKey;
	}

	public void setReviewKey(ReviewKey reviewKey) {
		this.reviewKey = reviewKey;
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + this.reviewKey.hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		Review review = (Review) obj;
		if (this.reviewKey.equals(review.reviewKey)) return false;
		
		return true;
	}
	
}
