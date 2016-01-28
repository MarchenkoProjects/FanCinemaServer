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

@Entity
@Table(name = "personality")
public class PersonEntity {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", 
					  strategy = "increment")
	@Column(name = "id_person",
			columnDefinition = "INT(10) UNSIGNED")
	private int idPerson;
	
	@Column(name = "full_name",
			length = 30,
			nullable = false)
	private String fullName;
	
	@Column(name = "first_name",
			length = 15,
			nullable = true)
	private String firstName;
	
	@Column(name = "last_name",
			length = 15,
			nullable = true)
	private String lastName;
	
	@Column(name = "was_born",
			nullable = true)
	private Date wasBorn;
	
	@Column(name = "biography",
			nullable = true,
			columnDefinition = "TEXT")
	private String biography;
	
	@ManyToMany(mappedBy = "directors")
	private Set<FilmEntity> directorFilms;
	
	@ManyToMany(mappedBy = "actors")
	private Set<FilmEntity> actorFilms;
	
	@ManyToMany(mappedBy = "writers")
	private Set<FilmEntity> writerFilms;
	
	@ManyToMany(mappedBy = "producers")
	private Set<FilmEntity> producerFilms;
	
	protected PersonEntity() {}

}
