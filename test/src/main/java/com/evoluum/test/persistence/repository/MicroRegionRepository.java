package com.evoluum.test.persistence.repository;

import com.evoluum.test.persistence.entity.MicroRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroRegionRepository extends JpaRepository<MicroRegion, Integer> {
}
