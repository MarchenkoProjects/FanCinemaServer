package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Byte> {

}
