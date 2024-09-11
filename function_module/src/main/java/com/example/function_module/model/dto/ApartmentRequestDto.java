package com.example.function_module.model.dto;

import com.example.function_module.model.entity.AddressEntity;
import com.example.function_module.model.entity.BedEntity;
import com.example.function_module.model.entity.embedded_apartment.Specifications;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentRequestDto {

    private String nameApartment;

    private Integer numberRooms;

    private Integer squareApartment;

    private String telephone;

    private Integer priceDay;

    private String availability;

    private Specifications specifications;

    private AddressEntity addressEntity;

    private Set<BedEntity> bedEntitySet;

}
