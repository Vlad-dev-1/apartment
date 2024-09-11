package com.example.function_module.model.dto;

import com.example.function_module.model.entity.ApartmentEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingAndCommentApartmentDto {

    private Byte rating;

    private String comment;

    private ApartmentEntity apartmentEntity;

}
