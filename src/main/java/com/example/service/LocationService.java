package com.example.service;

import com.example.dto.AddLocationsRequest;
import com.example.dto.LocationGroupDTO;
import com.example.entity.Location;
import com.example.entity.LocationGroup;
import java.util.List;
import java.util.UUID;

public interface LocationService {
    LocationGroup createLocationGroup(LocationGroupDTO request);
    List<Location> addLocationsToGroup(Long groupId, AddLocationsRequest request);
    
    // New methods
    List<Location> getAllLocations(UUID businessId);
    List<Location> getLocationsByGroup(Long groupId, UUID businessId);
    Location getLocationById(Long locationId);
    
    List<LocationGroup> getAllLocationGroups(UUID businessId);
}