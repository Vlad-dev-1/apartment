package com.example.function_module.model.dto;

import com.example.function_module.model.entity.ApartmentEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressApartmentDto {

    private String country;

    private String region;

    private Integer postcode;

    private String city;

    private String street;

    private Integer houseNumber;

    private Integer numberFlat;

}
