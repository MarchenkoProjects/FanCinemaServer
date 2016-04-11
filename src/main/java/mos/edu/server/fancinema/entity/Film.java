package mos.edu.server.fancinema.entity;

import java.io.Serializable;
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
import mos.edu.server.fancinema.entity.represent.Rating;

@Entity
@Table(name = Constants.TABLE.FILMS)
@JsonInclude(Include.NON_EMPTY)
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_FILM = "id_film";
	public static final String COLUMN_ID_FILM_KINOPOISK = "id_film_kinopoisk";
	public static final String COLUMN_ORIGINAL_NAME = "original_name";
	public static final String COLUMN_ALTERNATIVE_NAME = "alternative_name";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_DURATION = "duration";
	public static final String COLUMN_SLOGAN = "slogan";
	public static final String COLUMN_BUDGET = "budget";
	public static final String COLUMN_WORLD_FEES = "world_fees";
	
	public static final String JOIN_COLUMN_FILM_ID = "film_id";
	public static final String JOIN_COLUMN_GENRE_ID = "genre_id";
	public static final String JOIN_COLUMN_COUNTRY_ID = "country_id";
	
	public static final String JOIN_COLUMN_WRITER_ID = "writer_id";
	public static final String JOIN_COLUMN_PRODUCER_ID = "producer_id";
	public static final String JOIN_COLUMN_DIRECTOR_ID = "director_id";
	public static final String JOIN_COLUMN_ACTOR_ID = "actor_id";
	
	public static final String FILMS_MAPPED_USERS = "film";
	
	@Id
	@GeneratedValue(generator = Constants.INCREMENT_GENERATOR)
	@GenericGenerator(name = Constants.INCREMENT_GENERATOR, strategy = Constants.INCREMENT_GENERATOR)
	@Column(name = COLUMN_ID_FILM, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	@Column(name = COLUMN_ID_FILM_KINOPOISK, nullable = true, columnDefinition = "BIGINT(20)")
	private long idFilmKinopoisk;
	
	@Transient
	private String posterUrl;

	@Column(name = COLUMN_ORIGINAL_NAME, length = 60, nullable = false)
	private String originalName;
	
	@Column(name = COLUMN_ALTERNATIVE_NAME, length = 60, nullable = false)
	private String alternativeName;
	
	@Column(name = COLUMN_YEAR, nullable = false, columnDefinition = "SMALLINT(4) UNSIGNED")
	private short year;
	
	@Column(name = COLUMN_DESCRIPTION, nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@Column(name = COLUMN_DURATION, nullable = false, columnDefinition = "SMALLINT(3) UNSIGNED")
	private short duration;
	
	@Column(name = COLUMN_SLOGAN, nullable = true, columnDefinition = "TEXT")
	private String slogan;
	
	@Column(name = COLUMN_BUDGET, nullable = true, columnDefinition = "INT(10) UNSIGNED")
	private long budget;
	
	@Column(name = COLUMN_WORLD_FEES, nullable = true, columnDefinition = "INT(10) UNSIGNED")
	private long worldFees;
	
	@Transient
	private Rating rating;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_GENRE,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_GENRE_ID, referencedColumnName = Genre.COLUMN_ID_GENRE))
	private Set<Genre> genres;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_COUNTRY,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_COUNTRY_ID, referencedColumnName = Country.COLUMN_ID_COUNTRY))
	private Set<Country> countries;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_WRITER,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_WRITER_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> writers;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_PRODUCER,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_PRODUCER_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> producers;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_DIRECTOR,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_DIRECTOR_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> directors;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_ACTOR,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_ACTOR_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> actors;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RatingFilm> ratingFilms;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Favorite> favorite;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Review> reviews;
	
	protected Film() {}
	
	protected Film(Film film) {
		this.idFilm = film.idFilm;
		this.posterUrl = film.posterUrl;
		this.originalName = film.originalName;
		this.alternativeName = film.alternativeName;
		this.year = film.year;
		this.description = film.description;
		this.duration = film.duration;
		this.slogan = film.slogan;
		this.budget = film.budget;
		this.worldFees = film.worldFees;
		this.genres = film.genres;
		this.countries = film.countries;
		this.writers = film.writers;
		this.producers = film.producers;
		this.directors = film.directors;
		this.actors = film.actors;
		
		this.ratingFilms = film.ratingFilms;
		this.favorite = film.favorite;
		this.reviews = film.reviews;
	}
	
	public Film(Film film, Double rating, long votesCount) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getWorldFees() {
		return worldFees;
	}

	public void setWorldFees(long worldFees) {
		this.worldFees = worldFees;
	}

	public Rating getRating() {
		return rating;
	}

	/*public void setRating(Rating rating) {
		this.rating = rating;
	}*/

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public Set<Person> getWriters() {
		return writers;
	}

	public void setWriters(Set<Person> writers) {
		this.writers = writers;
	}

	public Set<Person> getProducers() {
		return producers;
	}

	public void setProducers(Set<Person> producers) {
		this.producers = producers;
	}

	public Set<Person> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<Person> directors) {
		this.directors = directors;
	}

	public Set<Person> getActors() {
		return actors;
	}

	public void setActors(Set<Person> actors) {
		this.actors = actors;
	}

	public Set<RatingFilm> getRatingFilms() {
		return ratingFilms;
	}

	public void setRatingFilms(Set<RatingFilm> ratingFilms) {
		this.ratingFilms = ratingFilms;
	}

	public Set<Favorite> getFavorite() {
		return favorite;
	}

	public void setFavorite(Set<Favorite> favorite) {
		this.favorite = favorite;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + this.idFilm;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		Film film = (Film) obj;
		if (this.idFilm != film.idFilm) return false;
		
		return true;
	}
	
}
