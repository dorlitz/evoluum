package com.evoluum.test.persistence.repository;

import com.evoluum.test.persistence.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> {
    County findByName(String cityName);
}
