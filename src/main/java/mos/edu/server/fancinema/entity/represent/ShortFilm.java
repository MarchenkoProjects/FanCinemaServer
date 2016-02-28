package mos.edu.server.fancinema.entity.represent;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Country;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.Genre;
import mos.edu.server.fancinema.entity.Person;
import mos.edu.server.fancinema.entity.RatingFilm;

@Entity
@Table(name = Constants.TABLE_FILMS)
@JsonInclude(Include.NON_EMPTY)
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
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_GENRE,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_GENRE_ID, referencedColumnName = Genre.COLUMN_ID_GENRE))
	private Set<Genre> genres;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_COUNTRY,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_COUNTRY_ID, referencedColumnName = Country.COLUMN_ID_COUNTRY))
	private Set<Country> countries;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_WRITER,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_WRITER_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> writers;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_PRODUCER,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_PRODUCER_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> producers;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_DIRECTOR,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_DIRECTOR_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> directors;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_ACTOR,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_ACTOR_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> actors;
	
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
		this.writers = shortFilm.writers;
		this.producers = shortFilm.producers;
		this.directors = shortFilm.directors;
		this.actors = shortFilm.actors;
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
