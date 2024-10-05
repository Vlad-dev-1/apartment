package com.example.function_module.controller;


import com.example.function_module.controller.controller_consts.ControllerConsts;
import com.example.function_module.model.dto.ApartmentRequestDto;
import com.example.function_module.model.dto.ApartmentResponceDto;
import com.example.function_module.model.dto.RatingAndCommentApartmentDto;
import com.example.function_module.model.dto.integration_with_geocoder_dto.GeoInfoDto;
import com.example.function_module.service.ApartmentService;
import com.example.function_module.service.CheckValidateTokenService;
import com.example.function_module.service.IntegrationApartmentWithGeocoderService;
import com.example.function_module.service.RatingAndCommentApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.function_module.controller.controller_consts.ControllerConsts.BASE_URL_APARTMENT_CONTROLLER;


@RestController
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final CheckValidateTokenService checkValidateTokenService;
    private final RatingAndCommentApartmentService ratingAndCommentApartmentService;
    private final IntegrationApartmentWithGeocoderService integrationApartmentWithGeocoderService;

    @PostMapping(ControllerConsts.REGISTRATION_APARTMENT)
    public String registrApartment(@RequestBody ApartmentRequestDto apartmentRequestDto, @RequestHeader String token) {

        checkValidateTokenService.checkToken(token);

        return apartmentService.saveApartment(apartmentRequestDto);
    }

    @PostMapping(ControllerConsts.REGISTRATION_RAITING_COMMENT_APARTMENT)
    public String registrRaitingCommentApartment(@RequestBody RatingAndCommentApartmentDto ratingAndCommentApartmentDto) {

        return ratingAndCommentApartmentService.saveRatingCommentApartment(ratingAndCommentApartmentDto);
    }

    @PostMapping(ControllerConsts.APARTMENT_BY_LOCATION)
    public List<ApartmentResponceDto> findApartmentByLocation(@RequestBody GeoInfoDto geoInfoDto) {

        return integrationApartmentWithGeocoderService.findApartmentByLocation(geoInfoDto);
    }

    @GetMapping(ControllerConsts.APARTMENT_BY_ID)
    public ApartmentResponceDto getApartmentInfo(@PathVariable Long id) {

        return apartmentService.getApartmentInfoById(id);
    }
}
