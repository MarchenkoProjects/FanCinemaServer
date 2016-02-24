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
	
	public static final String URI_HOME = "/";
	public static final String URI_FILMS = "/films";
	public static final String URI_PERSONS = "/persons";
	public static final String URI_GENRES = "/genres";
	public static final String URI_COUNTRIES = "/countries";
	
}
