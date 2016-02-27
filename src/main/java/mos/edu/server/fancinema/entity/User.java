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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_USERS)
public class User implements Serializable {
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
	
	@Transient
	private String avatarUrl;
	
	@Column(name = COLUMN_LOGIN, length = 15, nullable = false)
	private String login;
	
	@Column(name = COLUMN_PASSWORD, length = 32, nullable = false)
	private String password;
	
	@Column(name = COLUMN_EMAIL, length = 30, nullable = false)
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RatingFilm> ratingFilms;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Favorite> favorite;
	
	@JsonIgnore
	@OneToMany(mappedBy = FILMS_MAPPED_USERS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Review> reviews;
	
	protected User() {}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getAvatarUrl() {
		this.avatarUrl = Constants.URL_FOR_PICTURES + String.valueOf(idUser) + Constants.PICTURES_EXTENSION;
		return avatarUrl;
	}

	/*public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}*/

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	/*public String getPassword() {
		return password;
	}*/

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RatingFilm> getRatingFilms() {
		return ratingFilms;
	}

	public void setRatingFilms(Set<RatingFilm> ratingFilms) {
		this.ratingFilms = ratingFilms;
	}

	public Set<Favorite> getFavorite() {
		return favorite;
	}

	public void setFavorite(Set<Favorite> favorite) {
		this.favorite = favorite;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
}
