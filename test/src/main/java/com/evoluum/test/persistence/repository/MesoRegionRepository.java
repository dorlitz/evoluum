package com.evoluum.test.persistence.repository;

import com.evoluum.test.persistence.entity.MesoRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MesoRegionRepository extends JpaRepository<MesoRegion, Integer> {

    @Query(value = "SELECT * FROM meso_region LIMIT 1", nativeQuery = true)
    MesoRegion getOneResult();

    @Query(value = "SELECT * FROM meso_region WHERE federation_id = :uf LIMIT 1", nativeQuery = true)
    MesoRegion getOneResult(Integer uf);
}
