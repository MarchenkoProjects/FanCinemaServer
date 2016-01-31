package mos.edu.server.fancinema.entity;

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
@Table(name = Constants.TABLE_GENRES)
public class GenreEntity {
	private static final String COLUMN_ID_GENRE = "id_genre";
	private static final String COLUMN_GENRE_EN = "genre_en";
	private static final String COLUMN_GENRE_RU = "genre_ru";
	
	private static final String GENRES_MAPPED_FILMS = "genres";
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = COLUMN_ID_GENRE, nullable = false, columnDefinition = "TINYINT(2) UNSIGNED")
	private byte idGenre;
	
	@Column(name = COLUMN_GENRE_EN, length = 20, nullable = true)
	private String genreEn;
	
	@Column(name = COLUMN_GENRE_RU, length = 20, nullable = false)
	private String genreRu;
	
	@ManyToMany(mappedBy = GENRES_MAPPED_FILMS)
	private Set<FilmEntity> films;
	
	protected GenreEntity() {}
}
