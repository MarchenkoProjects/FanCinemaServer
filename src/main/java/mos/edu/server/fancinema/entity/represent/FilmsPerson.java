package mos.edu.server.fancinema.entity.represent;

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

import com.fasterxml.jackson.annotation.JsonProperty;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Person;

@Entity
@Table(name = Constants.TABLE.PERSONS)
public class FilmsPerson {
	
	private static final String PROPERTY_WRITER = "writer";
	private static final String PROPERTY_PRODUCER = "producer";
	private static final String PROPERTY_DIRECTOR = "director";
	private static final String PROPERTY_ACTOR = "actor";
	
	@Id
	@GeneratedValue(generator = Constants.INCREMENT_GENERATOR)
	@GenericGenerator(name = Constants.INCREMENT_GENERATOR, strategy = Constants.INCREMENT_GENERATOR)
	@Column(name = Person.COLUMN_ID_PERSON, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idPerson;
	
	@JsonProperty(value = PROPERTY_WRITER)
	@ManyToMany(mappedBy = Person.WRITERS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmWriters;
	
	@JsonProperty(value = PROPERTY_PRODUCER)
	@ManyToMany(mappedBy = Person.PRODUCERS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmProducers;
	
	@JsonProperty(value = PROPERTY_DIRECTOR)
	@ManyToMany(mappedBy = Person.DIRECTORS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmDirectors;
	
	@JsonProperty(value = PROPERTY_ACTOR)
	@ManyToMany(mappedBy = Person.ACTORS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmActors;
	
	protected FilmsPerson() {}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	
	public Set<ShortFilm> getFilmWriters() {
		return filmWriters;
	}

	public void setFilmWriters(Set<ShortFilm> filmWriters) {
		this.filmWriters = filmWriters;
	}

	public Set<ShortFilm> getFilmProducers() {
		return filmProducers;
	}

	public void setFilmProducers(Set<ShortFilm> filmProducers) {
		this.filmProducers = filmProducers;
	}

	public Set<ShortFilm> getFilmDirectors() {
		return filmDirectors;
	}

	public void setFilmDirectors(Set<ShortFilm> filmDirectors) {
		this.filmDirectors = filmDirectors;
	}

	public Set<ShortFilm> getFilmActors() {
		return filmActors;
	}

	public void setFilmActors(Set<ShortFilm> filmActors) {
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
		
		FilmsPerson filmsPerson = (FilmsPerson) obj;
		if (this.idPerson != filmsPerson.idPerson) return false;
		
		return true;
	}
	
}
