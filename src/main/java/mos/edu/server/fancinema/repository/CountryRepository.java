package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mos.edu.server.fancinema.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Byte> {

}
