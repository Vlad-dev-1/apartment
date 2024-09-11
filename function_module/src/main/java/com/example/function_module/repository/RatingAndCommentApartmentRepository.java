package com.example.function_module.repository;

import com.example.function_module.model.entity.RatingAndCommentApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingAndCommentApartmentRepository extends JpaRepository<RatingAndCommentApartmentEntity,Long> {
}
