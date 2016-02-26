package mos.edu.server.fancinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.represent.UserFavorite;
import mos.edu.server.fancinema.entity.represent.UserReview;
import mos.edu.server.fancinema.repository.UserRepository;
import mos.edu.server.fancinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RatingFilm getUserRatingForFilm(int idUser, int idFilm) {
		return userRepository.findUserRatingForFilm(idUser, idFilm);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserReview> getUserReviews(int id, int page, int size) {
		Pageable pageRequest = new PageRequest(page, size);
		return userRepository.findUserReviews(id, pageRequest);
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

}
