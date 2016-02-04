package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.FavoriteEntity;
import mos.edu.server.fancinema.entity.composite_key.FavoriteKey;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteKey> {

}
