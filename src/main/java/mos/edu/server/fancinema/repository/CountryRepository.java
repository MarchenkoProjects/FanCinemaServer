package mos.edu.server.fancinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mos.edu.server.fancinema.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Byte> {

}
