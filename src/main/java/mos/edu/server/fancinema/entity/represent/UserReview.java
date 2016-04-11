package mos.edu.server.fancinema.entity.represent;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Review;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.composite_key.ReviewKey;

@Entity
@Table(name = Constants.TABLE.REVIEWS)
public class UserReview {

	@JsonIgnore
	@EmbeddedId
	private ReviewKey reviewKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = Review.JOIN_COLUMN_USER, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = Review.JOIN_COLUMN_FILM, insertable = false, updatable = false)
	private ShortFilm film;
	
	@Column(name = Review.COLUMN_HEADER, length = 50, nullable = false)
	private String header;
	
	@Column(name = Review.COLUMN_REVIEW, nullable = false, columnDefinition = "TEXT")
	private String review;
	
	protected UserReview() {}
	
	public ReviewKey getReviewKey() {
		return reviewKey;
	}

	public void setReviewKey(ReviewKey reviewKey) {
		this.reviewKey = reviewKey;
	}
	
	public ShortFilm getFilm() {
		return film;
	}

	public void setFilm(ShortFilm film) {
		this.film = film;
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
		
		UserReview userReview = (UserReview) obj;
		if (this.reviewKey.equals(userReview.reviewKey)) return false;
		
		return true;
	}
	
}
