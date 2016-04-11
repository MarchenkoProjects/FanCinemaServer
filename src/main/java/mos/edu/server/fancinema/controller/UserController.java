package mos.edu.server.fancinema.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mos.edu.server.fancinema.Constants;
import mos.edu.server.fancinema.entity.Favorite;
import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.Review;
import mos.edu.server.fancinema.entity.User;
import mos.edu.server.fancinema.entity.represent.UserFavorite;
import mos.edu.server.fancinema.entity.represent.UserReview;
import mos.edu.server.fancinema.service.UserService;

@RestController
@RequestMapping(value = Constants.URI.USERS)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<User>> getUsers(@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
										 	 		 @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<User> users = userService.getUsers(page, size);
		if (users.hasContent()) {
			final long lastPage = users.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(users.getSize(), users.getNumber(), users.getTotalElements(), lastPage);
			final PagedResources<User> usersResource = 
					new PagedResources<>(users.getContent(), metadata);
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				usersResource.add(linkTo(methodOn(UserController.class).getUsers(prevPage, size)).withRel("prev"));
			usersResource.add(linkTo(methodOn(UserController.class).getUsers(page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				usersResource.add(linkTo(methodOn(UserController.class).getUsers(nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(usersResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.USER_BY_ID,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<User>> getUser(@PathVariable(value = "id_user") int id) {
		if (id <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final User user = userService.getUserById(id);
		if (user != null) {
			final Resource<User> userResource = new Resource<>(user);
			userResource.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
			userResource.add(linkTo(methodOn(UserController.class).getUserReviews(id, 0, 30)).withRel("reviews"));
			userResource.add(linkTo(methodOn(UserController.class).getUserFavorite(id, 0, 30)).withRel("favorite"));
			userResource.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, true, 0, 30)).withRel("favorite-looked"));
			userResource.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, false, 0, 30)).withRel("favorite-not-looked"));
			return new ResponseEntity<>(userResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					params = {"login"},
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<User>> findUserByLogin(@RequestParam(value = "login") String login) {
		if (login.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final User user = userService.getUserByLogin(login);
		if (user != null) {
			final Resource<User> userResource = new Resource<>(user);
			userResource.add(linkTo(methodOn(UserController.class).findUserByLogin(login)).withSelfRel());
			final int idUser = user.getIdUser();
			userResource.add(linkTo(methodOn(UserController.class).getUserReviews(idUser, 0, 30)).withRel("reviews"));
			userResource.add(linkTo(methodOn(UserController.class).getUserFavorite(idUser, 0, 30)).withRel("favorite"));
			userResource.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(idUser, true, 0, 30)).withRel("favorite-looked"));
			userResource.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(idUser, false, 0, 30)).withRel("favorite-not-looked"));
			return new ResponseEntity<>(userResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<User>> addUser(@RequestBody User user) {
		final User addedUser = userService.addUser(user);
		if(addedUser != null)
			return getUser(user.getIdUser());
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.USER_RATING_FOR_FILM,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<RatingFilm>> getUserRatingForFilm(@PathVariable(value = "id_user") int idUser,
																 @PathVariable(value = "id_film") int idFilm) {
		if (idUser <= 0 || idFilm <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final RatingFilm filmRating = userService.getUserRatingForFilm(idUser, idFilm);
		if (filmRating != null) {
			final Resource<RatingFilm> filmRatingResource = new Resource<>(filmRating);
			filmRatingResource.add(linkTo(methodOn(UserController.class).getUser(idUser)).withRel("self-user"));
			filmRatingResource.add(linkTo(methodOn(FilmController.class).getFilm(idFilm)).withRel("self-film"));
			filmRatingResource.add(linkTo(methodOn(UserController.class).getUserRatingForFilm(idUser, idFilm)).withSelfRel());
			return new ResponseEntity<>(filmRatingResource, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.POST,
					value = Constants.URI.USER_RATING_FOR_FILM,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<RatingFilm>> addUserRatingForFilm(@PathVariable(value = "id_user") int idUser,
											  	  			 	 @PathVariable(value = "id_film") int idFilm,
											  	  			 	 @RequestParam(value = "value") byte value) {
		if (idUser <= 0 || idFilm <= 0 || value <= 0 || value > 10) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final RatingFilm addedRatingFilm = userService.addUserRatingForFilm(idUser, idFilm, value);
		if (addedRatingFilm != null) 
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.USER_REVIEWS,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<UserReview>> getUserReviews(@PathVariable(value = "id_user") int id,
														  	     @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
														  	     @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (id <= 0 || page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<UserReview> reviews = userService.getUserReviews(id, page, size);
		if (reviews != null && reviews.hasContent()) {
			final long lastPage = reviews.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(reviews.getSize(), reviews.getNumber(), reviews.getTotalElements(), lastPage);
			final PagedResources<UserReview> reviewResources = 
					new PagedResources<>(reviews.getContent(), metadata);
			reviewResources.add(linkTo(methodOn(UserController.class).getUser(id)).withRel("self-user"));
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				reviewResources.add(linkTo(methodOn(UserController.class).getUserReviews(id, prevPage, size)).withRel("prev"));
			reviewResources.add(linkTo(methodOn(UserController.class).getUserReviews(id, page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				reviewResources.add(linkTo(methodOn(UserController.class).getUserReviews(id, nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(reviewResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.POST,
					value = Constants.URI.USER_FILM_REVIEW,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Resource<Review>> addUserReviewForFilm(@PathVariable(value = "id_user") int idUser,
													  		 @PathVariable(value = "id_film") int idFilm,
													  		 @RequestBody Review review) {
		if (idUser <= 0 || idFilm <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Review addedReview = userService.addUserReview(idUser, idFilm, review);
		if (addedReview != null) 
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.USER_FAVORITE,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<UserFavorite>> getUserFavorite(@PathVariable(value = "id_user") int id,
													  	        	@RequestParam(value = "page", required = false, defaultValue = "0") int page, 
													  	        	@RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (id <= 0 || page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<UserFavorite> favorite = userService.getUserFavorite(id, page, size);
		if (favorite != null && favorite.hasContent()) {
			final long lastPage = favorite.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(favorite.getSize(), favorite.getNumber(), favorite.getTotalElements(), lastPage);
			final PagedResources<UserFavorite> favoriteResources = 
					new PagedResources<>(favorite.getContent(), metadata);
			favoriteResources.add(linkTo(methodOn(UserController.class).getUser(id)).withRel("self-user"));
			favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, true, 0, 30)).withRel("favorite-looked"));
			favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, false, 0, 30)).withRel("favorite-not-looked"));
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavorite(id, prevPage, size)).withRel("prev"));
			favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavorite(id, page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavorite(id, nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(favoriteResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET,
					value = Constants.URI.USER_FAVORITE,
					params = {"looked"},
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<UserFavorite>> getUserFavoriteIsLooked(@PathVariable(value = "id_user") int id,
																			@RequestParam(value = "looked", required = false, defaultValue = "0") boolean looked,
																  	        @RequestParam(value = "page", required = false, defaultValue = "0") int page, 
																  	        @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
		if (id <= 0 || page < 0 || size <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Page<UserFavorite> favorite = userService.getUserFavoriteIsLooked(id, looked, page, size);
		if (favorite != null && favorite.hasContent()) {
			final long lastPage = favorite.getTotalPages();
			final PageMetadata metadata = 
					new PageMetadata(favorite.getSize(), favorite.getNumber(), favorite.getTotalElements(), lastPage);
			final PagedResources<UserFavorite> favoriteResources = 
					new PagedResources<>(favorite.getContent(), metadata);
			favoriteResources.add(linkTo(methodOn(UserController.class).getUser(id)).withRel("self-user"));
			favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavorite(id, page, size)).withRel("favorite"));
			if (looked)
				favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, false, 0, 30)).withRel("favorite-not-looked"));
			else
				favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, true, 0, 30)).withRel("favorite-looked"));
			
			final int prevPage = page - 1;
			if (prevPage >= 0)
				favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, looked, prevPage, size)).withRel("prev"));
			favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, looked, page, size)).withSelfRel());
			final int nextPage = page + 1;
			if (nextPage < lastPage)
				favoriteResources.add(linkTo(methodOn(UserController.class).getUserFavoriteIsLooked(id, looked, nextPage, size)).withRel("next"));
			
			return new ResponseEntity<>(favoriteResources, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.POST,
					value = Constants.URI.USER_FILM_FAVORITE,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<PagedResources<UserFavorite>> addUserFavorite(@PathVariable(value = "id_user") int idUser,
															        @PathVariable(value = "id_film") int idFilm,
															        @RequestParam(value = "looked", required = false, defaultValue = "0") boolean looked) {
		if (idUser <= 0 || idFilm <= 0) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		final Favorite addedFavorite = userService.addUserFavorite(idUser, idFilm, looked);
		if (addedFavorite != null)
			return getUserFavorite(idUser, 0, 30);
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
}
