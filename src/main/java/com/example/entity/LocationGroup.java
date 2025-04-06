package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "location_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "locationGroup", cascade = CascadeType.ALL)
    private List<Location> locations;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
}