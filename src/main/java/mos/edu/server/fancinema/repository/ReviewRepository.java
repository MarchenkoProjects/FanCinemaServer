package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.ReviewEntity;
import mos.edu.server.fancinema.entity.composite_key.ReviewKey;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, ReviewKey> {

}
