package mos.edu.server.fancinema.entity.composite_key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FavoriteKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_FILM_ID = "film_id";

	@Column(name = "user_id", nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idUser;
	
	@Column(name = "film_id", nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	protected FavoriteKey() {}

	public FavoriteKey(int idUser, int idFilm) {
		this.idUser = idUser;
		this.idFilm = idFilm;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + this.idUser;
		result = prime * result + this.idFilm;
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		FavoriteKey favoriteKey = (FavoriteKey) obj;
		if (this.idUser != favoriteKey.idUser) return false;
		if (this.idFilm != favoriteKey.idFilm) return false;
		
		return true;
	}
	
}
