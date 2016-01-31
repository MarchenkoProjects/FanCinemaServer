package mos.edu.server.fancinema.entity;

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

import org.hibernate.annotations.GenericGenerator;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_FILMS)
public class FilmEntity {
	private static final String COLUMN_ID_FILM = "id_film";
	private static final String COLUMN_ORIGINAL_NAME = "original_name";
	private static final String COLUMN_ALTERNATIVE_NAME = "alternative_name";
	private static final String COLUMN_YEAR = "year";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_DURATION = "duration";
	private static final String COLUMN_SLOGAN = "slogan";
	private static final String COLUMN_BUDGET = "budget";
	private static final String COLUMN_WORLD_FEES = "worls_fees";
		
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = COLUMN_ID_FILM, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	@Column(name = COLUMN_ORIGINAL_NAME, length = 60, nullable = false)
	private String originalName;
	
	@Column(name = COLUMN_ALTERNATIVE_NAME, length = 60, nullable = false)
	private String alternativeName;
	
	@Column(name = COLUMN_YEAR, nullable = false, columnDefinition = "SMALLINT(4) UNSIGNED")
	private short year;
	
	@Column(name = COLUMN_DESCRIPTION, nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@Column(name = COLUMN_DURATION, nullable = false, columnDefinition = "TINYINT(3) UNSIGNED")
	private byte duration;
	
	@Column(name = COLUMN_SLOGAN, nullable = true, columnDefinition = "TEXT")
	private String slogan;
	
	@Column(name = COLUMN_BUDGET, nullable = true, columnDefinition = "INT(10) UNSIGNED")
	private int budget;
	
	@Column(name = COLUMN_WORLD_FEES, nullable = true, columnDefinition = "INT(10) UNSIGNED")
	private int worldFees;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_GENRE,
			   joinColumns = @JoinColumn(name = "film_id", referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id_genre"))
	private Set<GenreEntity> genres;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_COUNTRY,
			   joinColumns = @JoinColumn(name = "film_id", referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id_country"))
	private Set<CountryEntity> countries;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_WRITER,
			   joinColumns = @JoinColumn(name = "film_id", referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "id_person"))
	private Set<PersonEntity> writers;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_PRODUCER,
			   joinColumns = @JoinColumn(name = "film_id", referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = "producer_id", referencedColumnName = "id_person"))
	private Set<PersonEntity> producers;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_DIRECTOR,
			   joinColumns = @JoinColumn(name = "film_id", referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id_person"))
	private Set<PersonEntity> directors;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.JOIN_TABLE_FILM_ACTOR,
			   joinColumns = @JoinColumn(name = "film_id", referencedColumnName = COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id_person"))
	private Set<PersonEntity> actors;
	
	@OneToMany(mappedBy = "ratingFilmKey.film")
	private Set<RatingFilmEntity> ratingFilms;
	
	@OneToMany(mappedBy = "favoriteKey.film")
	private Set<FavoriteEntity> favorite;
	
	@OneToMany(mappedBy = "reviewKey.film")
	private Set<ReviewEntity> reviews;
	
	protected FilmEntity() {}
	
}
