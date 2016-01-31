package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.FavoriteEntity;
import mos.edu.server.fancinema.entity.compound_key.FavoriteKey;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteKey> {

}
