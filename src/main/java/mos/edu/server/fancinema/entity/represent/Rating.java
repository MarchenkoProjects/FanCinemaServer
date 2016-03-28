package mos.edu.server.fancinema.entity.represent;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.composite_key.RatingFilmKey;

@Entity
@Table(name = Constants.TABLE.RATING_FILM)
public class Rating {

	@JsonIgnore
	@EmbeddedId
	private RatingFilmKey ratingFilmKey;
	
	@Transient
	private double rating;
	
	@Transient
	private long votesCount;
	
	protected Rating() {}
	
	public Rating(Double rating, long votesCount) {
		if (rating != null)
			this.rating = rating;
		this.votesCount = votesCount;
	}

	public RatingFilmKey getRatingFilmKey() {
		return ratingFilmKey;
	}

	public void setRatingFilmKey(RatingFilmKey ratingFilmKey) {
		this.ratingFilmKey = ratingFilmKey;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public long getVotesCount() {
		return votesCount;
	}

	public void setVotesCount(long votesCount) {
		this.votesCount = votesCount;
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
		
		Rating rating = (Rating) obj;
		if (!this.ratingFilmKey.equals(rating.ratingFilmKey)) return false;
		
		return true;
	}
	
}
