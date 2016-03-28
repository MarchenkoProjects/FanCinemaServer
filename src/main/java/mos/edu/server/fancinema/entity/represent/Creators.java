package mos.edu.server.fancinema.entity.represent;

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

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Film;
import mos.edu.server.fancinema.entity.Person;

@Entity
@Table(name = Constants.TABLE.FILMS)
public class Creators {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = Film.COLUMN_ID_FILM, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_WRITER,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_WRITER_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> writers;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_PRODUCER,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_PRODUCER_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> producers;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_DIRECTOR,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_DIRECTOR_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> directors;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = Constants.TABLE.JOIN_FILM_ACTOR,
			   joinColumns = @JoinColumn(name = Film.JOIN_COLUMN_FILM_ID, referencedColumnName = Film.COLUMN_ID_FILM),
			   inverseJoinColumns = @JoinColumn(name = Film.JOIN_COLUMN_ACTOR_ID, referencedColumnName = Person.COLUMN_ID_PERSON))
	private Set<Person> actors;
	
	protected Creators() {}
	
	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
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
		
		Creators creators = (Creators) obj;
		if (this.idFilm != creators.idFilm) return false;
		
		return true;
	}
	
}
