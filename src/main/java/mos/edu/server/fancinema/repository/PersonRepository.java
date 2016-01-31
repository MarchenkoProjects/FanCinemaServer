package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

}
