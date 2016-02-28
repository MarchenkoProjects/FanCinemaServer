package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.Favorite;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.Review;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.represent.UserFavorite;
import mos.edu.server.fancinema.entity.represent.UserReview;

public interface UserService {

	Page<User> getUsers(int page, int size);
	User getUserById(int id);
	User addUser(User user);
	
	RatingFilm getUserRatingForFilm(int idUser, int idFilm);
	RatingFilm addUserRatingForFilm(int idUser, int idFilm, byte value);
	
	Page<UserReview> getUserReviews(int id, int page, int size);
	Review addUserReview(int idUser, int idFilm, Review review);
	
	Page<UserFavorite> getUserFavorite(int id, int page, int size);
	Page<UserFavorite> getUserFavoriteIsLooked(int id, boolean looked, int page, int size);
	Favorite addUserFavorite(int idUser, int idFilm, boolean looked);
	
}
