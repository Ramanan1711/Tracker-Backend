package com.example.controller;

import com.example.dto.AddLocationsRequest;
import com.example.dto.LocationGroupDTO;
import com.example.entity.Location;
import com.example.entity.LocationGroup;  // Add this import
import com.example.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {
    
    private final LocationService locationService;

    @PostMapping("/groups")
    public ResponseEntity<?> createLocationGroup(@RequestBody LocationGroupDTO request) {
        return ResponseEntity.ok(locationService.createLocationGroup(request));
    }

    @PostMapping("/groups/{groupId}/addresses")
    public ResponseEntity<?> addLocationsToGroup(
            @PathVariable Long groupId,
            @RequestBody AddLocationsRequest request) {
        return ResponseEntity.ok(locationService.addLocationsToGroup(groupId, request));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Location>> getAllLocations(@PathVariable UUID businessId) {
        return ResponseEntity.ok(locationService.getAllLocations(businessId));
    }

    @GetMapping("/groups/{groupId}/locations")
    public ResponseEntity<List<Location>> getLocationsByGroup(
            @PathVariable Long groupId,
            @RequestParam UUID businessId) {
        return ResponseEntity.ok(locationService.getLocationsByGroup(groupId, businessId));
    }

    @GetMapping("/groups/business/{businessId}")
    public ResponseEntity<List<LocationGroup>> getAllLocationGroups(@PathVariable UUID businessId) {
        return ResponseEntity.ok(locationService.getAllLocationGroups(businessId));
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long locationId) {
        return ResponseEntity.ok(locationService.getLocationById(locationId));
    }
}