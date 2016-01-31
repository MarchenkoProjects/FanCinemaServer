package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Byte> {

}
