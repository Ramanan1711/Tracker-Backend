//package com.example.dto;
//
//public class UserResponse {
//}


package com.example.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private String role;
    private String location;
    private UUID businessId;
    private String businessName;
}
