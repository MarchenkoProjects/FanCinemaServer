package mos.edu.server.fancinema.entity.composite_key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReviewKey implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_FILM_ID = "film_id";
	
	@Column(name = "user_id", nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idUser;
	
	@Column(name = "film_id", nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idFilm;
	
	protected ReviewKey() {}

	public ReviewKey(int idUser, int idFilm) {
		super();
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
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		
		ReviewKey reviewKey = (ReviewKey) obj;
		if (this.idUser != reviewKey.idUser) return false;
		if (this.idFilm != reviewKey.idFilm) return false;
		
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
