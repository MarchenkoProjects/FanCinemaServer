package mos.edu.server.fancinema.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import mos.edu.server.fancinema.Constants;

@Entity
@Table(name = Constants.TABLE_USERS)
public class UserEntity {
	private static final String COLUMN_ID_USER = "id_user";
	private static final String COLUMN_LOGIN = "login";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_EMAIL = "email";
	
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
	
	@OneToMany(mappedBy = "ratingFilmKey.user")
	private Set<RatingFilmEntity> ratingFilms;
	
	@OneToMany(mappedBy = "favoriteKey.user")
	private Set<FavoriteEntity> favorite;
	
	@OneToMany(mappedBy = "reviewKey.user")
	private Set<ReviewEntity> reviews;
	
	protected UserEntity() {}
}
