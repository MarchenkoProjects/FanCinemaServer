package mos.edu.server.fancinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.represent.UserFavorite;
import mos.edu.server.fancinema.entity.represent.UserReview;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	String FIND_USER_RATING_FOR_FILM = 
		"SELECT new RatingFilm(ratingFilm.rating) " + 
		"FROM RatingFilm ratingFilm " + 
		"WHERE ratingFilm.ratingFilmKey.idUser = :idUser " + 
		"AND ratingFilm.ratingFilmKey.idFilm = :idFilm";
	@Query(FIND_USER_RATING_FOR_FILM)
	RatingFilm findUserRatingForFilm(@Param("idUser") int idUser, @Param("idFilm") int idFilm);
	
	String FIND_USER_REVIEWS = 
		"SELECT userReview " +
		"FROM UserReview userReview " +
		"JOIN userReview.user user " +
		"WHERE user.idUser = :id";
	@Query(FIND_USER_REVIEWS)
	Page<UserReview> findUserReviews(@Param("id") int id, Pageable pageRequest);
	
	String FIND_USER_FAVORITE = 
		"SELECT userFavorite " +
		"FROM UserFavorite userFavorite " +
		"JOIN userFavorite.user user " +
		"WHERE user.idUser = :id";
	@Query(FIND_USER_FAVORITE)
	Page<UserFavorite> findUserFavorite(@Param("id") int id, Pageable pageRequest);
	
	String FIND_USER_FAVORITE_IS_LOOKED = 
		"SELECT userFavorite " +
		"FROM UserFavorite userFavorite " +
		"JOIN userFavorite.user user " +
		"WHERE user.idUser = :id " +
		"AND " +
		"userFavorite.looked = :looked";
	@Query(FIND_USER_FAVORITE_IS_LOOKED)
	Page<UserFavorite> findUserFavoriteIsLooked(@Param("id") int id, @Param("looked") boolean looked, Pageable pageRequest);
	
}
