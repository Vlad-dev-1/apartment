package com.example.function_module.service;

import com.example.function_module.model.dto.ApartmentResponceDto;
import com.example.function_module.model.dto.integration_with_geocoder_dto.GeoInfoDto;

import java.util.List;

public interface IntegrationApartmentWithGeocoderService {

    public String integrationWithGeocoder(GeoInfoDto geoInfoDto);

    List<ApartmentResponceDto> findApartmentByLocation(GeoInfoDto geoInfoDto);
}
