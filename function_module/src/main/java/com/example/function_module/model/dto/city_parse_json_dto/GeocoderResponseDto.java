package com.example.function_module.model.dto.city_parse_json_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class GeocoderResponseDto {

    @JsonProperty(value = "results")
    List<ResultDto> resultsList;
}
