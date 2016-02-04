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

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_FILMS)
public class FilmEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_FILM = "id_film";
	public static final String COLUMN_ORIGINAL_NAME = "original_name";
	public static final String COLUMN_ALTERNATIVE_NAME = "alternative_name";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_DURATION = "duration";
	public static final String COLUMN_SLOGAN = "slogan";
	public static final String COLUMN_BUDGET = "budget";
	public static final String COLUMN_WORLD_FEES = "world_fees";
	
	private static final String JOIN_COLUMN_FILM_ID = "film_id";
	private static final String JOIN_COLUMN_GENRE_ID = "genre_id";
	private static final String JOIN_COLUMN_COUNTRY_ID = "country_id";
	
	private static final String JOIN_COLUMN_WRITER_ID = "writer_id";
	private static final String JOIN_COLUMN_PRODUCER_ID = "producer_id";
	private static final String JOIN_COLUMN_DIRECTOR_ID = "director_id";
	private static final String JOIN_COLUMN_ACTOR_ID = "actor_id";
	
	private static final String FILMS_MAPPED_USERS = "film";
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = COLUMN_ID_FILM, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
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
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_GENRE,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_GENRE_ID, referencedColumnName = GenreEntity.COLUMN_ID_GENRE))
	private Set<GenreEntity> genres;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_COUNTRY,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_COUNTRY_ID, referencedColumnName = CountryEntity.COLUMN_ID_COUNTRY))
	private Set<CountryEntity> countries;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_WRITER,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_WRITER_ID, referencedColumnName = PersonEntity.COLUMN_ID_PERSON))
	private Set<PersonEntity> writers;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_PRODUCER,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_PRODUCER_ID, referencedColumnName = PersonEntity.COLUMN_ID_PERSON))
	private Set<PersonEntity> producers;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_DIRECTOR,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_DIRECTOR_ID, referencedColumnName = PersonEntity.COLUMN_ID_PERSON))
	private Set<PersonEntity> directors;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_ACTOR,
			   joinColumns = @JoinColumn(name = JOIN_COLUMN_FILM_ID, referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_ACTOR_ID, referencedColumnName = PersonEntity.COLUMN_ID_PERSON))
	private Set<PersonEntity> actors;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RatingFilmEntity> ratingFilms;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FavoriteEntity> favorite;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ReviewEntity> reviews;
	
	protected FilmEntity() {}

	public FilmEntity(int idFilm, String originalName, String alternativeName, short year, String description,
			byte duration, String slogan, int budget, int worldFees) {
		this.idFilm = idFilm;
		this.originalName = originalName;
		this.alternativeName = alternativeName;
		this.year = year;
		this.description = description;
		this.duration = duration;
		this.slogan = slogan;
		this.budget = budget;
		this.worldFees = worldFees;
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

	public Set<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreEntity> genres) {
		this.genres = genres;
	}

	public Set<CountryEntity> getCountries() {
		return countries;
	}

	public void setCountries(Set<CountryEntity> countries) {
		this.countries = countries;
	}

	public Set<PersonEntity> getWriters() {
		return writers;
	}

	public void setWriters(Set<PersonEntity> writers) {
		this.writers = writers;
	}

	public Set<PersonEntity> getProducers() {
		return producers;
	}

	public void setProducers(Set<PersonEntity> producers) {
		this.producers = producers;
	}

	public Set<PersonEntity> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<PersonEntity> directors) {
		this.directors = directors;
	}

	public Set<PersonEntity> getActors() {
		return actors;
	}

	public void setActors(Set<PersonEntity> actors) {
		this.actors = actors;
	}

	public Set<RatingFilmEntity> getRatingFilms() {
		return ratingFilms;
	}

	public void setRatingFilms(Set<RatingFilmEntity> ratingFilms) {
		this.ratingFilms = ratingFilms;
	}

	public Set<FavoriteEntity> getFavorite() {
		return favorite;
	}

	public void setFavorite(Set<FavoriteEntity> favorite) {
		this.favorite = favorite;
	}

	public Set<ReviewEntity> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewEntity> reviews) {
		this.reviews = reviews;
	}
	
}
