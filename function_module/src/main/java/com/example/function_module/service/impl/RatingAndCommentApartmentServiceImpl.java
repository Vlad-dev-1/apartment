package com.example.function_module.service.impl;

import com.example.function_module.exception.ApartmentInfoException;
import com.example.function_module.mapper.RatingAndCommentApartmentMapper;
import com.example.function_module.model.dto.RatingAndCommentApartmentDto;
import com.example.function_module.model.entity.ApartmentEntity;
import com.example.function_module.model.entity.RatingAndCommentApartmentEntity;
import com.example.function_module.repository.ApartmentRepository;
import com.example.function_module.repository.RatingAndCommentApartmentRepository;
import com.example.function_module.service.RatingAndCommentApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.function_module.exception.ApartmentInfoException.APARTMENT_NOT_FOUND_DATABASE;

@Service
@RequiredArgsConstructor
public class RatingAndCommentApartmentServiceImpl implements RatingAndCommentApartmentService {

    private final RatingAndCommentApartmentRepository ratingAndCommentApartmentRepository;

    private final ApartmentRepository apartmentRepository;

    private final RatingAndCommentApartmentMapper ratingAndCommentApartmentMapper;

    public static final String RATING_AND_COMMENT_SAVE_DONE = "Рейтин и комментарий сохранен";

    //Сохранение комментария и рейтинга апартамента (связь One-to-Many)
    @Override
    @Transactional
    public String saveRatingCommentApartment(RatingAndCommentApartmentDto ratingAndCommentApartmentDto) {

        RatingAndCommentApartmentEntity ratingAndCommentApartmentEntity = ratingAndCommentApartmentMapper
                .mappingRatingAndCommentApartment(ratingAndCommentApartmentDto);

        ApartmentEntity apartmentEntity = ratingAndCommentApartmentEntity.getApartmentEntity();

        List<ApartmentEntity> apartmentRepositoryAll = apartmentRepository.findAll();

        ApartmentEntity apartmentEntityFromDataBase = apartmentRepositoryAll
                .stream()
                .filter(apartmentEntity1 -> apartmentEntity1
                        .equals(apartmentEntity))
                .findFirst()
                .orElseThrow(() -> new ApartmentInfoException(APARTMENT_NOT_FOUND_DATABASE, 705));

        ratingAndCommentApartmentEntity.setApartmentEntity(apartmentEntityFromDataBase);

        ratingAndCommentApartmentRepository.saveAndFlush(ratingAndCommentApartmentEntity);

        return RATING_AND_COMMENT_SAVE_DONE;
    }

}
