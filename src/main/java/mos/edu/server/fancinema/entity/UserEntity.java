package mos.edu.server.fancinema.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_USERS)
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String COLUMN_ID_USER = "id_user";
	public static final String COLUMN_LOGIN = "login";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_EMAIL = "email";
	
	private static final String FILMS_MAPPED_USERS = "user";
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = COLUMN_ID_USER, nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int idUser;
	
	@Column(name = COLUMN_LOGIN, length = 15, nullable = false)
	private String login;
	
	@Column(name = COLUMN_PASSWORD, length = 32, nullable = false)
	private String password;
	
	@Column(name = COLUMN_EMAIL, length = 30, nullable = false)
	private String email;
	
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RatingFilmEntity> ratingFilms;
	
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FavoriteEntity> favorite;
	
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ReviewEntity> reviews;
	
	protected UserEntity() {}

	public UserEntity(int idUser, String login, String password, String email) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RatingFilmEntity> getRatingFilms() {
		return ratingFilms;
	}

	public void setRatingFilms(Set<RatingFilmEntity> ratingFilms) {
		this.ratingFilms = ratingFilms;
	}

	public Set<FavoriteEntity> getFavorite() {
		return favorite;
	}

	public void setFavorite(Set<FavoriteEntity> favorite) {
		this.favorite = favorite;
	}

	public Set<ReviewEntity> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewEntity> reviews) {
		this.reviews = reviews;
	}
	
}
