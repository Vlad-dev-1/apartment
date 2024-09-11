package com.example.function_module.mapper;

import com.example.function_module.model.dto.AddressApartmentDto;
import com.example.function_module.model.dto.ApartmentRequestDto;
import com.example.function_module.model.dto.ApartmentResponceDto;
import com.example.function_module.model.dto.BedDto;
import com.example.function_module.model.entity.AddressEntity;
import com.example.function_module.model.entity.ApartmentEntity;
import com.example.function_module.model.entity.BedEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ApartmentMapper {

    ApartmentEntity mappingApartmentDtoToEntity(ApartmentRequestDto apartmentRequestDto);

    ApartmentResponceDto mappingApartmentEntityToApartmentResponceDto(ApartmentEntity apartmentEntity);

    public default Set<BedEntity> mappingApartmentDtoToBedEntitySet(ApartmentRequestDto apartmentRequestDto){
        return apartmentRequestDto.getBedEntitySet();
    }


    List<ApartmentResponceDto> mappingApartmentEntityListToApartmentDtoList(List<ApartmentEntity> apartmentEntityList);

    @AfterMapping
    public default void afterMappingApartmentEntityListToApartmentDtoList(@MappingTarget List<ApartmentResponceDto> apartmentResponceDtos,
                                                                  List<ApartmentEntity> apartmentEntityList){
        for (ApartmentEntity a: apartmentEntityList) {
            for (ApartmentResponceDto get: apartmentResponceDtos) {
                get.setAddressApartmentDto(mappingAddressApartmentEntityToApartmentDto(a.getAddressEntity()));
                get.setBedDtoSet(mappingBedEntityToBedDto(a.getBedEntitySet()));
            }

        }
    }

    public AddressApartmentDto mappingAddressApartmentEntityToApartmentDto(AddressEntity addressEntity);

    public Set<BedDto> mappingBedEntityToBedDto(Set<BedEntity> bedEntity);

}
