package com.example.function_module.model.entity;

import com.example.function_module.model.entity.embedded_apartment.Specifications;
import com.example.function_module.model.entity.enum_apartment.Availability;
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
@Table(name = "apartment_info")
public class ApartmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apatrmentSequence")
    @SequenceGenerator(name = "apatrmentSequence", sequenceName = "apatrment_sequence", allocationSize = 1, initialValue = 2)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "name_apartment")
    private String nameApartment;

    @Column(name = "number_rooms")
    private Integer numberRooms;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "square_apartment")
    private Integer squareApartment;

    @Column(name = "price_day")
    private Integer priceDay;

    @Column(name = "availability")
    @Enumerated(value = EnumType.STRING)
    private Availability availability;

    @Embedded
    private Specifications specifications;


    @OneToOne(mappedBy = "apartmentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private AddressEntity addressEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "apartment_bed_connection", joinColumns = {
            @JoinColumn(name = "apartment_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "bed_id", referencedColumnName = "id")}
    )
    @EqualsAndHashCode.Exclude
    @Builder.Default
//    @ToString.Exclude
    private Set<BedEntity> bedEntitySet = new HashSet<>();


    @OneToMany(mappedBy = "apartmentEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private List<RatingAndCommentApartmentEntity> ratingAndCommentApartmentEntityList = new ArrayList<>();


    public void addBedEntity(BedEntity bedEntity){
        bedEntitySet.add(bedEntity);
        bedEntity.getApartmentEntitySet().add(this);
    }
}
