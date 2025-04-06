package com.example.dto;

import lombok.Data;
import java.util.Set;

@Data
public class LocationDTO {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String category;
    private Set<Long> assignedUserIds;
}