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
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COLUMN_LOOKED = "looked";

	@EmbeddedId
	private FavoriteKey favoriteKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private Film film;
	
	@Column(name = COLUMN_LOOKED, nullable = false, columnDefinition = "BIT(1) DEFAULT b'0'")
	private boolean looked;
	
	protected Favorite() {}
	
	public FavoriteKey getFavoriteKey() {
		return favoriteKey;
	}

	public void setFavoriteKey(FavoriteKey favoriteKey) {
		this.favoriteKey = favoriteKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public boolean isLooked() {
		return looked;
	}

	public void setLooked(boolean looked) {
		this.looked = looked;
	}
	
}
