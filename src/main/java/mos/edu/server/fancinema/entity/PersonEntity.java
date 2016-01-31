package mos.edu.server.fancinema.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_PERSONALITY)
public class PersonEntity {
	private static final String COLUMN_ID_PERSON = "id_person";
	private static final String COLUMN_FULL_NAME = "full_name";
	private static final String COLUMN_FIRST_NAME = "first_name";
	private static final String COLUMN_LAST_NAME = "last_name";
	private static final String COLUMN_WAS_BORN = "was_born";
	private static final String COLUMN_BIOGRAPHY = "biography";
	
	private static final String WRITERS_MAPPED_FILMS = "writers";
	private static final String PRODUCERS_MAPPED_FILMS = "producers";
	private static final String DIRECTORS_MAPPED_FILMS = "directors";
	private static final String ACTORS_MAPPED_FILMS = "actors";

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = COLUMN_ID_PERSON, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idPerson;
	
	@Column(name = COLUMN_FULL_NAME, length = 30, nullable = false)
	private String fullName;
	
	@Column(name = COLUMN_FIRST_NAME, length = 15, nullable = true)
	private String firstName;
	
	@Column(name = COLUMN_LAST_NAME, length = 15, nullable = true)
	private String lastName;
	
	@Column(name = COLUMN_WAS_BORN, nullable = true)
	private Date wasBorn;
	
	@Column(name = COLUMN_BIOGRAPHY, nullable = true, columnDefinition = "TEXT")
	private String biography;
	
	@ManyToMany(mappedBy = WRITERS_MAPPED_FILMS)
	private Set<FilmEntity> filmWriters;
	
	@ManyToMany(mappedBy = PRODUCERS_MAPPED_FILMS)
	private Set<FilmEntity> filmProducers;
	
	@ManyToMany(mappedBy = DIRECTORS_MAPPED_FILMS)
	private Set<FilmEntity> filmDirectors;
	
	@ManyToMany(mappedBy = ACTORS_MAPPED_FILMS)
	private Set<FilmEntity> filmActors;
	
	protected PersonEntity() {}
}
