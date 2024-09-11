package com.example.function_module.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rating_comment_apartment_info")
public class RatingAndCommentApartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingCommentApartmentSequence")
    @SequenceGenerator(name = "ratingCommentApartmentSequence", sequenceName = "rating_comment_apartment_sequence", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating_apartment")
    private Byte rating;

    @Column(name = "comment_apatment", length = 500)
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "apartment_id")
    @ToString.Exclude
    private ApartmentEntity apartmentEntity;
}
