package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.RatingFilmEntity;
import mos.edu.server.fancinema.entity.compound_key.RatingKey;

public interface RatingFilmRepository extends JpaRepository<RatingFilmEntity, RatingKey> {

}
