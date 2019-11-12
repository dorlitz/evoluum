package com.evoluum.test.persistence.repository;

import com.evoluum.test.persistence.entity.Federation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FederationRepository extends JpaRepository<Federation, Integer> {
    @Query(value = "SELECT f.id as estateId, f.initials AS stateInitials, r.name AS regionName, " +
            " c.name AS cityName, meso.name AS mesoName FROM federation f " +
            "    INNER JOIN meso_region meso ON meso.federation_id = f.id " +
            "    INNER JOIN micro_region micro ON micro.mesoregion_id = meso.id " +
            "    INNER JOIN region r ON r.id = f.region_id " +
            "    INNER JOIN county c ON c.micro_region_id = micro.id " +
            "    WHERE f.id = :ufId", nativeQuery = true)

    List<Map<String, Object>> getResume(Integer ufId);

}
