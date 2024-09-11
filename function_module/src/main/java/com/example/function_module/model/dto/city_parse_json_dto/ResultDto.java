package com.example.function_module.model.dto.city_parse_json_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResultDto {

    private ComponentsDto components;
}
