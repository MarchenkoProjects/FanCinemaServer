package mos.edu.server.fancinema.entity.represent;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Favorite;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.composite_key.FavoriteKey;

@Entity
@Table(name = Constants.TABLE.FAVORITE)
public class UserFavorite {

	@JsonIgnore
	@EmbeddedId
	private FavoriteKey favoriteKey;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = Favorite.JOIN_COLUMN_USER, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = Favorite.JOIN_COLUMN_FILM, insertable = false, updatable = false)
	private ShortFilm film;
	
	@Column(name = Favorite.COLUMN_LOOKED, nullable = false, columnDefinition = "BIT(1) DEFAULT b'0'")
	private boolean looked;
	
	protected UserFavorite() {}
	
	public FavoriteKey getFavoriteKey() {
		return favoriteKey;
	}

	public void setFavoriteKey(FavoriteKey favoriteKey) {
		this.favoriteKey = favoriteKey;
	}
	
	public ShortFilm getFilm() {
		return film;
	}

	public void setFilm(ShortFilm film) {
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
		
		UserFavorite userFavorite = (UserFavorite) obj;
		if (!this.favoriteKey.equals(userFavorite.favoriteKey)) return false;
		
		return true;
	}
	
}
