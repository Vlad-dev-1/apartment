package com.example.function_module.repository;

import com.example.function_module.model.entity.BedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<BedEntity, Long> {

    @Modifying
    @Query(value = "update BedEntity b set b.nameBed = :nameBedValue WHERE b.id = :idBedValue")
    void updateToBedEntity(@Param("nameBedValue") String nameBedValue, @Param("idBedValue") Long idBedValue);
}
