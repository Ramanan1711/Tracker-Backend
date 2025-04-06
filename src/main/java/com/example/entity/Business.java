package com.example.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "businesses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "business_id", updatable = false, nullable = false)
    private UUID businessId;

    @Column(name = "business_name", nullable = false, unique = true)
    private String businessName;

//    public static Object builder() {
//    }
}
