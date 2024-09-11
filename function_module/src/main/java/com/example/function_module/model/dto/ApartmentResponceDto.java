package com.example.function_module.model.dto;

import com.example.function_module.model.entity.embedded_apartment.Specifications;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentResponceDto {

    private String nameApartment;

    private Integer numberRooms;

    private Integer squareApartment;

    private String telephone;

    private Integer priceDay;

    private String availability;

    private Specifications specifications;

    private AddressApartmentDto addressApartmentDto;

    private Set<BedDto> bedDtoSet;

}
