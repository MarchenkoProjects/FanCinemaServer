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
@Table(name = Constants.TABLE.PERSONS)
@JsonInclude(Include.NON_EMPTY)
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_PERSON = "id_person";
	public static final String COLUMN_ID_PERSON_KINOPOISK = "id_person_kinopoisk";
	public static final String COLUMN_NAME_RU = "name_ru";
	public static final String COLUMN_NAME_EN = "name_en";
	public static final String COLUMN_WAS_BORN = "was_born";
	public static final String COLUMN_BIOGRAPHY = "biography";
	
	public static final String WRITERS_MAPPED_FILMS = "writers";
	public static final String PRODUCERS_MAPPED_FILMS = "producers";
	public static final String DIRECTORS_MAPPED_FILMS = "directors";
	public static final String ACTORS_MAPPED_FILMS = "actors";

	@Id
	@GeneratedValue(generator = Constants.INCREMENT_GENERATOR)
	@GenericGenerator(name = Constants.INCREMENT_GENERATOR, strategy = Constants.INCREMENT_GENERATOR)
	@Column(name = COLUMN_ID_PERSON, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idPerson;
	
	@Column(name = COLUMN_ID_PERSON_KINOPOISK, nullable = true, columnDefinition = "BIGINT(20)")
	private long idFilmKinopoisk;
	
	@Transient
	private String fotoUrl;
	
	@Column(name = COLUMN_NAME_RU, length = 30, nullable = false)
	private String nameRu;
	
	@Column(name = COLUMN_NAME_EN, length = 15, nullable = true)
	private String nameEn;
	
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
		this.fotoUrl = Constants.URL_FOR_PERSON_PICTURES + String.valueOf(idPerson) + Constants.PICTURES_EXTENSION;
		return fotoUrl;
	}

	/*public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}*/
	
	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + this.idPerson;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		Person person = (Person) obj;
		if (this.idPerson != person.idPerson) return false;
		
		return true;
	}
	
}
