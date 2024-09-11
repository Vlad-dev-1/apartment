package com.example.function_module.service.impl;


import com.example.function_module.exception.AddressInfoException;
import com.example.function_module.exception.ApartmentInfoException;
import com.example.function_module.mapper.ApartmentMapper;
import com.example.function_module.model.dto.ApartmentResponceDto;
import com.example.function_module.model.dto.city_parse_json_dto.GeocoderResponseDto;
import com.example.function_module.model.dto.integration_with_geocoder_dto.GeoInfoDto;
import com.example.function_module.model.entity.ApartmentEntity;
import com.example.function_module.model.entity.IntegrationInfoEntity;
import com.example.function_module.repository.AddressRepository;
import com.example.function_module.repository.ApartmentRepository;
import com.example.function_module.repository.IntegrationRepository;
import com.example.function_module.service.IntegrationApartmentWithGeocoderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.function_module.exception.AddressInfoException.CITY_BY_APARTMENT_NOT_FOUND;
import static com.example.function_module.exception.AddressInfoException.RESULT_DTO_IS_EMPTY;
import static com.example.function_module.exception.ApartmentInfoException.APARTMENT_NOT_FOUND_DATABASE_LOCATION_CLIENT;
import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegrationApartmentWithGeocoderServiceImpl implements IntegrationApartmentWithGeocoderService {

    private final AddressRepository addressRepository;
    private final IntegrationRepository integrationRepository;
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepository apartmentRepository;


    @Override
    public List<ApartmentResponceDto> findApartmentByLocation(GeoInfoDto geoInfoDto) {

        String cityApartment = integrationWithGeocoder(geoInfoDto);

        List<ApartmentEntity> apartmentEntityList = apartmentRepository.findApartmentEntityByAddressEntityByCity(cityApartment);
        if(isNull(apartmentEntityList)){
            throw new ApartmentInfoException(APARTMENT_NOT_FOUND_DATABASE_LOCATION_CLIENT, 700);
        }
        apartmentEntityList.stream().forEach(apartmentEntity -> System.out.println(apartmentEntity));

        return apartmentMapper.mappingApartmentEntityListToApartmentDtoList(apartmentEntityList);
    }

    @Override
    public String integrationWithGeocoder(GeoInfoDto geoInfoDto) {

        RestTemplate restTemplate = new RestTemplate();
        GeocoderResponseDto geoCity = restTemplate
                .exchange(preparePathForGEOIntegration(geoInfoDto.getLatitude()
                                , geoInfoDto.getLongitude()
                                , "GEO"),
                        HttpMethod.GET,
                        new HttpEntity<>(null, null),
                        GeocoderResponseDto.class)
                .getBody();

        if (geoCity.getResultsList().isEmpty()) {
            throw new AddressInfoException(RESULT_DTO_IS_EMPTY, 703);
        }

        String cityApartment = geoCity.getResultsList().get(0).getComponents().getCity();
        if (isNull(cityApartment)) {
            throw new AddressInfoException(CITY_BY_APARTMENT_NOT_FOUND, 703);
        }
        return cityApartment;
    }

    private String preparePathForGEOIntegration(String latitude, String longitude, String id) {

        IntegrationInfoEntity integrationInfoEntity = integrationRepository.findById(id).get();
        String decoderKey = Base64EncoderDecoder.decoder(integrationInfoEntity.getKeyInfo());

        return String.format(integrationInfoEntity.getPathInfo(), latitude, longitude, decoderKey);
    }



//    public String getCityParseJson(String json) {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            JsonNode jsonNode = objectMapper.readTree(json);
//
//            // Получаем массив "results"
//            JsonNode results = jsonNode.get("results");
//            if (results.isArray() && results.size() > 0) {
//
//                // Берем первый элемент массива
//                JsonNode firstResult = results.get(0);
//
//                // Получаем компонент "city"
//                String city = firstResult.get("components").get("city").asText();
//                log.info("Клиент находиться в городе {}", city);
//                return city;
//            }
//            throw new RuntimeException();
//        } catch (Exception e) {
//            throw new RuntimeException("Отсутствует информация по местонахождению клиента");
//        }
//    }

}
