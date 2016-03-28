package mos.edu.server.fancinema;

public final class Constants {
	
	public static final class TABLE {
		public static final String FILMS = "films";
		public static final String GENRES = "genres";
		public static final String COUNTRIES = "countries";
		public static final String PERSONS = "persons";
		
		public static final String JOIN_FILM_GENRE = "film_genre";
		public static final String JOIN_FILM_COUNTRY = "film_country";
		public static final String JOIN_FILM_WRITER = "film_writer";
		public static final String JOIN_FILM_PRODUCER = "film_producer";
		public static final String JOIN_FILM_DIRECTOR = "film_director";
		public static final String JOIN_FILM_ACTOR = "film_actor";
		
		public static final String RATING_FILM = "rating_films";
		public static final String FAVORITE = "favorite";
		public static final String REVIEWS = "reviews";
		public static final String USERS = "users";
	}
	
	public static final String URL_FOR_PICTURES = "/"; // ???
	public static final String PICTURES_EXTENSION = ".jpg";
	
	public static final class URI {
		public static final String HOME = "/";
		
		public static final String FILMS = "/films";
		public static final String FILM_BY_ID = "/{id_film}";
		public static final String FILM_GENRES = "/{id_film}/genres";
		public static final String FILM_COUNTRIES = "/{id_film}/countries";
		public static final String FILM_CREATORS = "/{id_film}/creators";
		public static final String FILM_WRITERS = "/{id_film}/writers";
		public static final String FILM_PRODUCERS = "/{id_film}/producers";
		public static final String FILM_DIRECTORS = "/{id_film}/directors";
		public static final String FILM_ACTORS = "/{id_film}/actors";
		public static final String FILM_RATING = "/{id_film}/rating";
		public static final String FILM_RATINGS = "/{id_film}/ratings";
		public static final String FILM_REVIEWS = "/{id_film}/reviews";
		
		public static final String GENRES = "/genres";
		public static final String GENRE_FILMS = "/{id_genre}/films";
		public static final String COUNTRIES = "/countries";
		public static final String COUNTRY_FILMS = "/{id_country}/films";
		
		public static final String PERSONS = "/persons";
		public static final String PERSON_BY_ID = "/{id_person}";
		public static final String PERSON_FILMS = "/{id_person}/films";
		
		public static final String USERS = "/users";
		public static final String USER_BY_ID = "/{id_user}";
		public static final String USER_RATING_FOR_FILM = "/{id_user}/films/{id_film}/rating";
		public static final String USER_REVIEWS = "/{id_user}/reviews";
		public static final String USER_FILM_REVIEW = "/{id_user}/films/{id_film}/review";
		public static final String USER_FAVORITE = "/{id_user}/favorite";
		public static final String USER_FILM_FAVORITE = "/{id_user}/films/{id_film}/favorite";
	}
	
}
