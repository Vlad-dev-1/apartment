package com.example.function_module.mapper;

import com.example.function_module.model.dto.RatingAndCommentApartmentDto;
import com.example.function_module.model.entity.RatingAndCommentApartmentEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RatingAndCommentApartmentMapper {

    RatingAndCommentApartmentEntity mappingRatingAndCommentApartment(RatingAndCommentApartmentDto ratingAndCommentApartmentDto);

}
