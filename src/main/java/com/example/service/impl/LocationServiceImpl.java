package com.example.service.impl;

import com.example.dto.AddLocationsRequest;
import com.example.dto.LocationGroupDTO;
import com.example.entity.*;
import com.example.repository.BusinessRepository;
import com.example.repository.LocationGroupRepository;
import com.example.repository.LocationRepository;
import com.example.repository.UserRepository;
import com.example.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationGroupRepository locationGroupRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    @Override
    @Transactional
    public LocationGroup createLocationGroup(LocationGroupDTO request) {
        Business business = businessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        LocationGroup group = LocationGroup.builder()
                .name(request.getName())
                .description(request.getDescription())
                .business(business)
                .build();

        return locationGroupRepository.save(group);
    }

    @Override
    @Transactional
    public List<Location> addLocationsToGroup(Long groupId, AddLocationsRequest request) {
        LocationGroup group = locationGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Location group not found"));

        List<Location> locations = request.getAddresses().stream()
                .map(dto -> {
                    Set<User> assignedUsers = dto.getAssignedUserIds().stream()
                            .map(userId -> userRepository.findById(userId)
                                    .orElseThrow(() -> new RuntimeException("User not found: " + userId)))
                            .collect(Collectors.toSet());

                    return Location.builder()
                            .streetAddress(dto.getStreetAddress())
                            .city(dto.getCity())
                            .state(dto.getState())
                            .zipCode(dto.getZipCode())
                            .country(dto.getCountry())
                            .category(LocationCategory.valueOf(dto.getCategory()))
                            .locationGroup(group)
                            .assignedUsers(assignedUsers)
                            .business(group.getBusiness())
                            .build();
                })
                .collect(Collectors.toList());

        return locationRepository.saveAll(locations);
    }

    @Override
    public List<Location> getAllLocations(UUID businessId) {
        return locationRepository.findByBusiness(businessId);
    }

    @Override
    public List<LocationGroup> getAllLocationGroups(UUID businessId) {
        return locationGroupRepository.findByBusinessId(businessId);
    }

    @Override
    public List<Location> getLocationsByGroup(Long groupId, UUID businessId) {
        // First verify the group exists and belongs to the business
        LocationGroup group = locationGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Location group not found"));
                
        if (!group.getBusiness().getBusinessId().equals(businessId)) {
            throw new RuntimeException("Location group does not belong to the specified business");
        }
        
        return locationRepository.findByLocationGroupAndBusiness(groupId, businessId);
    }

    @Override
    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }
    
    // Remove the unused getCurrentBusiness() method
}