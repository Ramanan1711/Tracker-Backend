package com.example.repository;

import com.example.entity.LocationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LocationGroupRepository extends JpaRepository<LocationGroup, Long> {
    @Query("SELECT lg FROM LocationGroup lg WHERE lg.business.businessId = :businessId")
    List<LocationGroup> findByBusinessId(@Param("businessId") UUID businessId);
}