package mos.edu.server.fancinema.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.composite_key.FavoriteKey;

@Entity
@Table(name = Constants.TABLE_FAVORITE)
public class FavoriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COLUMN_LOOKED = "looked";

	@EmbeddedId
	private FavoriteKey favoriteKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private FilmEntity film;
	
	@Column(name = COLUMN_LOOKED, nullable = false, columnDefinition = "BIT(1) DEFAULT b'0'")
	private boolean looked;
	
	protected FavoriteEntity() {}
	
	public FavoriteEntity(int idUser, int idFilm, boolean looked) {
		this.favoriteKey = new FavoriteKey(idUser, idFilm);
		this.looked = looked;
	}

	public FavoriteEntity(FavoriteKey favoriteKey, boolean looked) {
		this.favoriteKey = favoriteKey;
		this.looked = looked;
	}
	
	public FavoriteKey getFavoriteKey() {
		return favoriteKey;
	}

	public void setFavoriteKey(FavoriteKey favoriteKey) {
		this.favoriteKey = favoriteKey;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public FilmEntity getFilm() {
		return film;
	}

	public void setFilm(FilmEntity film) {
		this.film = film;
	}

	public boolean isLooked() {
		return looked;
	}

	public void setLooked(boolean looked) {
		this.looked = looked;
	}
	
}
