package com.example.repository;

import com.example.entity.Location;
import com.example.entity.Business;
import com.example.entity.LocationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l FROM Location l WHERE l.business.businessId = :businessId")
    List<Location> findByBusiness(@Param("businessId") UUID businessId);
    
    @Query("SELECT l FROM Location l WHERE l.locationGroup.id = :groupId AND l.business.businessId = :businessId")
    List<Location> findByLocationGroupAndBusiness(@Param("groupId") Long groupId, @Param("businessId") UUID businessId);
}