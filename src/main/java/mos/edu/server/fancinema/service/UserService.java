package mos.edu.server.fancinema.service;

import org.springframework.data.domain.Page;

import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.represent.UserFavorite;
import mos.edu.server.fancinema.entity.represent.UserReview;

public interface UserService {

	User getUserById(int id);
	RatingFilm getUserRatingForFilm(int idUser, int idFilm);
	Page<UserReview> getUserReviews(int id, int page, int size);
	Page<UserFavorite> getUserFavorite(int id, int page, int size);
	Page<UserFavorite> getUserFavoriteIsLooked(int id, boolean looked, int page, int size);
	
}
