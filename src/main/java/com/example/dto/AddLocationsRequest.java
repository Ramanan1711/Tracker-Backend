package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class AddLocationsRequest {
    private List<LocationDTO> addresses;
}