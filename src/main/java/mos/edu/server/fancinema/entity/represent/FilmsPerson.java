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
@Table(name = Constants.TABLE_PERSONALITY)
public class FilmsPerson {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = Person.COLUMN_ID_PERSON, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idPerson;
	
	@JsonProperty(value = "writer")
	@ManyToMany(mappedBy = Person.WRITERS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmWriters;
	
	@JsonProperty(value = "producer")
	@ManyToMany(mappedBy = Person.PRODUCERS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmProducers;
	
	@JsonProperty(value = "director")
	@ManyToMany(mappedBy = Person.DIRECTORS_MAPPED_FILMS, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShortFilm> filmDirectors;
	
	@JsonProperty(value = "actor")
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
	
}
