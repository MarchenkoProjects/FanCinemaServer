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
@Table(name = Constants.TABLE.FAVORITE)
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String JOIN_COLUMN_USER = "user_id";
	public static final String JOIN_COLUMN_FILM = "film_id";
	public static final String COLUMN_LOOKED = "looked";

	@JsonIgnore
	@EmbeddedId
	private FavoriteKey favoriteKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = JOIN_COLUMN_USER, insertable = false, updatable = false)
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = JOIN_COLUMN_FILM, insertable = false, updatable = false)
	private Film film;
	
	@Column(name = COLUMN_LOOKED, nullable = false, columnDefinition = "BIT(1) DEFAULT b'0'")
	private boolean looked;
	
	protected Favorite() {}
	
	public Favorite(FavoriteKey favoriteKey, boolean looked) {
		super();
		this.favoriteKey = favoriteKey;
		this.looked = looked;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + favoriteKey.hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		Favorite favorite = (Favorite) obj;
		if (!this.favoriteKey.equals(favorite.favoriteKey)) return false;
		
		return true;
	}
	
}
