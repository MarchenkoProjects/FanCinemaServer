package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.ReviewEntity;
import mos.edu.server.fancinema.entity.compound_key.ReviewKey;

public interface ReviewRepository extends JpaRepository<ReviewEntity, ReviewKey> {

}
