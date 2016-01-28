package mos.edu.server.fancinema.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "genres")
public class GenreEntity {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", 
					  strategy = "increment")
	@Column(name = "id_genre",
			columnDefinition = "TINYINT(2) UNSIGNED")
	private byte idGenre;
	
	@Column(name = "genre_en",
			length = 20,
			nullable = true)
	private String genreEn;
	
	@Column(name = "genre_ru",
			length = 20,
			nullable = false)
	private String genreRu;
	
	@ManyToMany(mappedBy = "genres")
	private Set<FilmEntity> films;
	
	protected GenreEntity() {}
	
}
