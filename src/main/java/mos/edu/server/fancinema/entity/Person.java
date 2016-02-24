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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_PERSONALITY)
@JsonInclude(Include.NON_EMPTY)
public class Person implements Serializable {
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
	
	@Transient
	private String fotoUrl;
	
	@Column(name = COLUMN_FULL_NAME, length = 30, nullable = false)
	private String fullName;
	
	@JsonIgnore
	@Column(name = COLUMN_FIRST_NAME, length = 15, nullable = true)
	private String firstName;
	
	@JsonIgnore
	@Column(name = COLUMN_LAST_NAME, length = 15, nullable = true)
	private String lastName;
	
	@Column(name = COLUMN_WAS_BORN, nullable = true)
	private Date wasBorn;
	
	@Column(name = COLUMN_BIOGRAPHY, nullable = true, columnDefinition = "TEXT")
	private String biography;
	
	@JsonIgnore
	@ManyToMany(mappedBy = WRITERS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Film> filmWriters;
	
	@JsonIgnore
	@ManyToMany(mappedBy = PRODUCERS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Film> filmProducers;
	
	@JsonIgnore
	@ManyToMany(mappedBy = DIRECTORS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Film> filmDirectors;
	
	@JsonIgnore
	@ManyToMany(mappedBy = ACTORS_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Film> filmActors;
	
	protected Person() {}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getFotoUrl() {
		this.fotoUrl = Constants.URL_FOR_PICTURES + String.valueOf(idPerson) + Constants.PICTURES_EXTENSION;
		return fotoUrl;
	}

	/*public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}*/

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

	public Set<Film> getFilmWriters() {
		return filmWriters;
	}

	public void setFilmWriters(Set<Film> filmWriters) {
		this.filmWriters = filmWriters;
	}

	public Set<Film> getFilmProducers() {
		return filmProducers;
	}

	public void setFilmProducers(Set<Film> filmProducers) {
		this.filmProducers = filmProducers;
	}

	public Set<Film> getFilmDirectors() {
		return filmDirectors;
	}

	public void setFilmDirectors(Set<Film> filmDirectors) {
		this.filmDirectors = filmDirectors;
	}

	public Set<Film> getFilmActors() {
		return filmActors;
	}

	public void setFilmActors(Set<Film> filmActors) {
		this.filmActors = filmActors;
	}
	
}
