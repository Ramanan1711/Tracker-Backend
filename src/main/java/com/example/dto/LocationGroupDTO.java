package com.example.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class LocationGroupDTO {
    private String name;
    private String description;
    private UUID businessId;
}