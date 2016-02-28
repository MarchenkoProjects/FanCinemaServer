package mos.edu.server.fancinema.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_GENRES)
@JsonInclude(Include.NON_EMPTY)
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_GENRE = "id_genre";
	public static final String COLUMN_GENRE_EN = "genre_en";
	public static final String COLUMN_GENRE_RU = "genre_ru";
	
	public static final String GENRES_MAPPED_FILMS = "genres";
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = COLUMN_ID_GENRE, nullable = false, columnDefinition = "TINYINT(2) UNSIGNED")
	private byte idGenre;
	
	@Column(name = COLUMN_GENRE_EN, length = 20, nullable = true)
	private String genreEn;
	
	@Column(name = COLUMN_GENRE_RU, length = 20, nullable = false)
	private String genreRu;
	
	@JsonIgnore
	@ManyToMany(mappedBy = GENRES_MAPPED_FILMS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Film> films;
	
	protected Genre() {}

	public byte getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(byte idGenre) {
		this.idGenre = idGenre;
	}

	public String getGenreEn() {
		return genreEn;
	}

	public void setGenreEn(String genreEn) {
		this.genreEn = genreEn;
	}

	public String getGenreRu() {
		return genreRu;
	}

	public void setGenreRu(String genreRu) {
		this.genreRu = genreRu;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}
	
}
