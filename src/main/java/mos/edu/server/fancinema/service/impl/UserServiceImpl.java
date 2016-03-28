package mos.edu.server.fancinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.Favorite;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.Review;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.composite_key.FavoriteKey;
import mos.edu.server.fancinema.entity.composite_key.RatingFilmKey;
import mos.edu.server.fancinema.entity.composite_key.ReviewKey;
import mos.edu.server.fancinema.entity.represent.UserFavorite;
import mos.edu.server.fancinema.entity.represent.UserReview;
import mos.edu.server.fancinema.repository.FavoriteRepository;
import mos.edu.server.fancinema.repository.RatingFilmRepository;
import mos.edu.server.fancinema.repository.ReviewRepository;
import mos.edu.server.fancinema.repository.UserRepository;
import mos.edu.server.fancinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RatingFilmRepository ratingFilmRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<User> getUsers(int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return userRepository.findAll(pageRequest);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findUserByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}
	
	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.saveAndFlush(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RatingFilm getUserRatingForFilm(int idUser, int idFilm) {
		return userRepository.findUserRatingForFilm(idUser, idFilm);
	}
	
	@Override
	@Transactional
	public RatingFilm addUserRatingForFilm(int idUser, int idFilm, byte value) {
		RatingFilm newRatingFilm = new RatingFilm(new RatingFilmKey(idUser, idFilm), value);
		return ratingFilmRepository.saveAndFlush(newRatingFilm);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserReview> getUserReviews(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return userRepository.findUserReviews(id, pageRequest);
	}
	
	@Override
	@Transactional
	public Review addUserReview(int idUser, int idFilm, Review review) {
		review.setReviewKey(new ReviewKey(idUser, idFilm));
		return reviewRepository.saveAndFlush(review);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UserFavorite> getUserFavorite(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return userRepository.findUserFavorite(id, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserFavorite> getUserFavoriteIsLooked(int id, boolean looked, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return userRepository.findUserFavoriteIsLooked(id, looked, pageRequest);
	}

	@Override
	@Transactional
	public Favorite addUserFavorite(int idUser, int idFilm, boolean looked) {
		Favorite newFavorite = new Favorite(new FavoriteKey(idUser, idFilm), looked);
		return favoriteRepository.saveAndFlush(newFavorite);
	}

}
