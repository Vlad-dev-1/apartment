package com.example.function_module.repository;

import com.example.function_module.model.entity.AddressEntity;
import com.example.function_module.model.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {

    ApartmentEntity findApartmentEntityByAddressEntity(AddressEntity addressEntity);


    @Query(value = "SELECT a from ApartmentEntity a left join fetch a.bedEntitySet " +
            "left join fetch a.ratingAndCommentApartmentEntityList " +
            "left join fetch a.addressEntity where a.addressEntity.city = :city")
    List<ApartmentEntity> findApartmentEntityByAddressEntityByCity(@Param("city") String city);
}
