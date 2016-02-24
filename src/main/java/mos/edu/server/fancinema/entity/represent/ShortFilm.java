package mos.edu.server.fancinema.entity.represent;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.RatingFilm;

@Entity
@Table(name = Constants.TABLE_FILMS)
public class ShortFilm {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = Film.COLUMN_ID_FILM, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	@Transient
	private String posterUrl;

	@Column(name = Film.COLUMN_ORIGINAL_NAME, length = 60, nullable = false)
	private String originalName;
	
	@Column(name = Film.COLUMN_ALTERNATIVE_NAME, length = 60, nullable = false)
	private String alternativeName;
	
	@Column(name = Film.COLUMN_YEAR, nullable = false, columnDefinition = "SMALLINT(4) UNSIGNED")
	private short year;
	
	@Transient
	private Rating rating;
	
	@JsonIgnore
	@OneToMany(mappedBy = Film.FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RatingFilm> ratingFilms;
	
	protected ShortFilm() {}
	
	protected ShortFilm(ShortFilm shortFilm) {
		this.idFilm = shortFilm.idFilm;
		this.posterUrl = shortFilm.posterUrl;
		this.originalName = shortFilm.originalName;
		this.alternativeName = shortFilm.alternativeName;
		this.year = shortFilm.year;
		this.ratingFilms = shortFilm.ratingFilms;
	}

	public ShortFilm(ShortFilm film, Double rating, long votesCount) {
		this(film);
		this.rating = new Rating(rating, votesCount);
	}
	
	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	
	public String getPosterUrl() {
		this.posterUrl = Constants.URL_FOR_PICTURES + String.valueOf(idFilm) + Constants.PICTURES_EXTENSION;
		return posterUrl;
	}

	/*public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}*/

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getAlternativeName() {
		return alternativeName;
	}

	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}
	
	public Rating getRating() {
		return rating;
	}

	/*public void setRating(Rating rating) {
		this.rating = rating;
	}*/
	
	public Set<RatingFilm> getRatingFilms() {
		return ratingFilms;
	}

	public void setRatingFilms(Set<RatingFilm> ratingFilms) {
		this.ratingFilms = ratingFilms;
	}
	
}
