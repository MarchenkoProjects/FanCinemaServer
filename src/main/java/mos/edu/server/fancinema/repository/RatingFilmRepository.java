package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.RatingFilm;
import mos.edu.server.fancinema.entity.composite_key.RatingFilmKey;

@Repository
public interface RatingFilmRepository extends JpaRepository<RatingFilm, RatingFilmKey> {

}
