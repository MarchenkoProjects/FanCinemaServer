package mos.edu.server.fancinema.entity;

import java.time.Year;
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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "films")
public class FilmEntity {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", 
					  strategy = "increment")
	@Column(name = "id_film", 
			columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	@Column(name = "original_name", 
			length = 60, 
			nullable = false)
	private String originalName;
	
	@Column(name = "alternative_name", 
			length = 60, 
			nullable = false)
	private String alternativeName;
	
	@Column(name = "year", 
			nullable = false,
			columnDefinition = "YEAR(4)")
	private Year year;
	
	@ManyToMany(cascade = CascadeType.PERSIST,
				fetch = FetchType.LAZY)
	@JoinTable(name = "film_genre",
			   joinColumns = @JoinColumn(name = "film_id",
					   					 referencedColumnName = "id_film"),
			   inverseJoinColumns = @JoinColumn(name = "genre_id",
					   							referencedColumnName = "id_genre"))
	private Set<GenreEntity> genres;
	
	@ManyToMany(cascade = CascadeType.PERSIST,
				fetch = FetchType.LAZY)
	@JoinTable(name = "film_director",
	   joinColumns = @JoinColumn(name = "film_id",
			   					 referencedColumnName = "id_film"),
	   inverseJoinColumns = @JoinColumn(name = "person_id",
			   							referencedColumnName = "id_person"))
	private Set<PersonEntity> directors;
	
	@ManyToMany(cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY)
	@JoinTable(name = "film_actor",
		joinColumns = @JoinColumn(name = "film_id",
		   					 	  referencedColumnName = "id_film"),
		inverseJoinColumns = @JoinColumn(name = "person_id",
		   								 referencedColumnName = "id_person"))
	private Set<PersonEntity> actors;
	
	@ManyToMany(cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY)
	@JoinTable(name = "film_writer",
		joinColumns = @JoinColumn(name = "film_id",
		   					 	  referencedColumnName = "id_film"),
		inverseJoinColumns = @JoinColumn(name = "person_id",
		   								 referencedColumnName = "id_person"))
	private Set<PersonEntity> writers;
	
	@ManyToMany(cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY)
	@JoinTable(name = "film_producer",
		joinColumns = @JoinColumn(name = "film_id",
		   					 	  referencedColumnName = "id_film"),
		inverseJoinColumns = @JoinColumn(name = "person_id",
		   								 referencedColumnName = "id_person"))
	private Set<PersonEntity> producers;

	@Column(name = "description", 
			nullable = false, 
			columnDefinition = "TEXT")
	private String description;
	
	@ManyToMany(cascade = CascadeType.PERSIST,
				fetch = FetchType.LAZY)
	@JoinTable(name = "film_country",
			   joinColumns = @JoinColumn(name = "film_id",
					   					 referencedColumnName = "id_film"),
			   inverseJoinColumns = @JoinColumn(name = "country_id",
					   							referencedColumnName = "id_country"))
	private Set<CountryEntity> countries;
	
	@Column(name = "duration", 
			nullable = false,
			columnDefinition = "TINYINT(3) UNSIGNED")
	private byte duration;
	
	@Column(name = "slogan", 
			nullable = true, 
			columnDefinition = "TEXT")
	private String slogan;
	
	@Column(name = "budget", 
			nullable = true, 
			columnDefinition = "INT(10) UNSIGNED")
	private int budget;
	
	@Column(name = "worls_fees", 
			nullable = true, 
			columnDefinition = "INT(10) UNSIGNED")
	private int worldFees;
	
	protected FilmEntity() {}
	
}
