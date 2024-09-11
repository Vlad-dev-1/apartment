package com.example.function_module.service;

import com.example.function_module.model.dto.ApartmentRequestDto;
import com.example.function_module.model.dto.ApartmentResponceDto;

public interface ApartmentService {

    String saveApartment(ApartmentRequestDto apartmentRequestDto);

    public ApartmentResponceDto getApartmentInfoById(Long id);

}
