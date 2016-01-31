package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.FilmEntity;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

}
