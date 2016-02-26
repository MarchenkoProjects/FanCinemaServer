package mos.edu.server.fancinema;

public final class Constants {

	public static final String DATABASE_NAME = "test_fan_cinema_bd";
	
	public static final String TABLE_FILMS = "films";
	public static final String TABLE_GENRES = "genres";
	public static final String TABLE_COUNTRIES = "countries";
	public static final String TABLE_PERSONALITY = "personality";
	
	public static final String JOIN_TABLE_FILM_GENRE = "film_genre";
	public static final String JOIN_TABLE_FILM_COUNTRY = "film_country";
	public static final String JOIN_TABLE_FILM_WRITER = "film_writer";
	public static final String JOIN_TABLE_FILM_PRODUCER = "film_producer";
	public static final String JOIN_TABLE_FILM_DIRECTOR = "film_director";
	public static final String JOIN_TABLE_FILM_ACTOR = "film_actor";
	
	public static final String TABLE_RATING_FILM = "rating_films";
	public static final String TABLE_FAVORITE = "favorite";
	public static final String TABLE_REVIEWS = "reviews";
	public static final String TABLE_USERS = "users";
	
	
	public static final String URL_FOR_PICTURES = "/"; // ???
	public static final String PICTURES_EXTENSION = ".jpg";
	
	// URIs
	public static final String URI_HOME = "/";
	
	public static final String URI_FILMS = "/films";
	public static final String URI_FILM_BY_ID = "/{id_film}";
	public static final String URI_FILM_GENRES = "/{id_film}/genres";
	public static final String URI_FILM_COUNTRIES = "/{id_film}/countries";
	public static final String URI_FILM_CREATORS = "/{id_film}/creators";
	public static final String URI_FILM_WRITERS = "/{id_film}/writers";
	public static final String URI_FILM_PRODUCERS = "/{id_film}/producers";
	public static final String URI_FILM_DIRECTORS = "/{id_film}/directors";
	public static final String URI_FILM_ACTORS = "/{id_film}/actors";
	public static final String URI_FILM_RATING = "/{id_film}/rating";
	public static final String URI_FILM_RATINGS = "/{id_film}/ratings";
	public static final String URI_FILM_REVIEWS = "/{id_film}/reviews";
	
	public static final String URI_GENRES = "/genres";
	public static final String URI_COUNTRIES = "/countries";
	
	public static final String URI_PERSONS = "/persons";
	public static final String URI_PERSON_BY_ID = "/{id_person}";
	public static final String URI_PERSON_FILMS = "/{id_person}/films";
	
	public static final String URI_USERS = "/users";
	public static final String URI_USER_BY_ID = "/{id_user}";
	public static final String URI_USER_RATING_FOR_FILM = "/{id_user}/films/{id_film}/rating";
	public static final String URI_USER_REVIEWS = "/{id_user}/reviews";
	public static final String URI_USER_FILM_REVIEW = "/{id_user}/films/{id_film}/review";
	public static final String URI_USER_FAVORITE = "/{id_user}/favorite";
	public static final String URI_USER_FILM_FAVORITE = "/{id_user}/films/{id_film}/favorite";
	
}
