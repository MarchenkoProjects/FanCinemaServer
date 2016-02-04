package mos.edu.server.fancinema.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_PERSONALITY)
public class PersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_PERSON = "id_person";
	public static final String COLUMN_FULL_NAME = "full_name";
	public static final String COLUMN_FIRST_NAME = "first_name";
	public static final String COLUMN_LAST_NAME = "last_name";
	public static final String COLUMN_WAS_BORN = "was_born";
	public static final String COLUMN_BIOGRAPHY = "biography";
	
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
	
	@ManyToMany(mappedBy = WRITERS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FilmEntity> filmWriters;
	
	@ManyToMany(mappedBy = PRODUCERS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FilmEntity> filmProducers;
	
	@ManyToMany(mappedBy = DIRECTORS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FilmEntity> filmDirectors;
	
	@ManyToMany(mappedBy = ACTORS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FilmEntity> filmActors;
	
	protected PersonEntity() {}

	public PersonEntity(int idPerson, String fullName, String firstName, String lastName, Date wasBorn,
			String biography) {
		this.idPerson = idPerson;
		this.fullName = fullName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wasBorn = wasBorn;
		this.biography = biography;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getWasBorn() {
		return wasBorn;
	}

	public void setWasBorn(Date wasBorn) {
		this.wasBorn = wasBorn;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<FilmEntity> getFilmWriters() {
		return filmWriters;
	}

	public void setFilmWriters(Set<FilmEntity> filmWriters) {
		this.filmWriters = filmWriters;
	}

	public Set<FilmEntity> getFilmProducers() {
		return filmProducers;
	}

	public void setFilmProducers(Set<FilmEntity> filmProducers) {
		this.filmProducers = filmProducers;
	}

	public Set<FilmEntity> getFilmDirectors() {
		return filmDirectors;
	}

	public void setFilmDirectors(Set<FilmEntity> filmDirectors) {
		this.filmDirectors = filmDirectors;
	}

	public Set<FilmEntity> getFilmActors() {
		return filmActors;
	}

	public void setFilmActors(Set<FilmEntity> filmActors) {
		this.filmActors = filmActors;
	}
	
}
