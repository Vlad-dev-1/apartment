package com.example.function_module.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bed_info")
public class BedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bedSequence")
    @SequenceGenerator(name = "bedSequence", sequenceName = "bed_sequence", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "name_bed", unique = true)
    private String nameBed;

    @ManyToMany(mappedBy = "bedEntitySet")
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ApartmentEntity> apartmentEntitySet = new HashSet<>();

}
