package com.example.function_module.repository;

import com.example.function_module.model.entity.AddressEntity;
import com.example.function_module.model.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query(value = "SELECT a FROM AddressEntity a where a.city = :city")
    List<AddressEntity> findAddressEntityByCity(@Param("city") String city);

}
