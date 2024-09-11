package com.example.function_module.service.impl;


import com.example.function_module.exception.ApartmentInfoException;
import com.example.function_module.mapper.ApartmentMapper;
import com.example.function_module.model.dto.ApartmentRequestDto;
import com.example.function_module.model.dto.ApartmentResponceDto;
import com.example.function_module.model.entity.AddressEntity;
import com.example.function_module.model.entity.ApartmentEntity;
import com.example.function_module.model.entity.BedEntity;
import com.example.function_module.repository.AddressRepository;
import com.example.function_module.repository.ApartmentRepository;
import com.example.function_module.repository.BedRepository;
import com.example.function_module.service.ApartmentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.function_module.exception.ApartmentInfoException.APARTMENT_FOUND_ADDRESS;
import static com.example.function_module.exception.ApartmentInfoException.APARTMENT_NOT_FOUND_DATABASE;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final EntityManager entityManager;

    private final ApartmentRepository apartmentRepository;

    private final BedRepository bedRepository;

    private final AddressRepository addressRepository;

    private final ApartmentMapper apartmentMapper;

    public static final String APARTMENT_SAVE_DONE = "Аппартаменты сохранены";

    public static final String SINGLE_BED = "односпальная";

    public static final String DOUBLE_BED = "двуспальная";



    @Override
    @Transactional
    public String saveApartment(ApartmentRequestDto apartmentRequestDto) {

        //Получение объекта ApartmentEntity из ApartmentDto и получение объекта AddressEntity, для последующей
        // проверки наличия апартамента в базе данных
        ApartmentEntity apartmentEntity = apartmentMapper.mappingApartmentDtoToEntity(apartmentRequestDto);
        AddressEntity addressEntity = apartmentEntity.getAddressEntity();

        //Проверка наличия апартамента в базе данных
        boolean apartmentEntityByAddressEntityAnyMatch = apartmentRepository
                .findAll()
                .stream()
                .anyMatch(apartmentEntity1 -> apartmentEntity1.getAddressEntity().equals(addressEntity));
        if (apartmentEntityByAddressEntityAnyMatch) {
            throw new ApartmentInfoException(APARTMENT_FOUND_ADDRESS, 703);
        }

        //Сохранение объекта BedEntity в базе данных и объединение BedEntity с
        // объектом ApartmentEntity (связь Many-to-Many)
        apartmentEntity.setBedEntitySet(new HashSet<>());
        Set<BedEntity> bedEntities = apartmentMapper.mappingApartmentDtoToBedEntitySet(apartmentRequestDto);
        Set<ApartmentEntity> apartmentEntityList = new HashSet<>();
        apartmentEntityList.add(apartmentEntity);

        for (ApartmentEntity apartEnt : apartmentEntityList) {

            List<BedEntity> bedRepositoryAll = bedRepository.findAll();

            if (bedRepositoryAll.isEmpty()) {
                bedRepository.saveAndFlush(BedEntity.builder()
                        .nameBed(SINGLE_BED)
                        .build());
                bedRepository.saveAndFlush(BedEntity.builder()
                        .nameBed(DOUBLE_BED)
                        .build());
            }

            List<BedEntity> bedRepositoryAllNotNull = bedRepository.findAll();

            if (!bedRepositoryAllNotNull.isEmpty()) {
                for (BedEntity bedEnt : bedRepositoryAllNotNull) {
                    if (bedEnt.getNameBed().equalsIgnoreCase(SINGLE_BED) && bedEntities.stream()
                            .anyMatch(bedEntity -> bedEntity
                                    .getNameBed()
                                    .equalsIgnoreCase(SINGLE_BED))) {
                        apartEnt.addBedEntity(bedEnt);
                        entityManager.merge(bedEnt);
                        entityManager.flush();
                    } else if (bedEnt.getNameBed().equalsIgnoreCase(DOUBLE_BED) && bedEntities.stream()
                            .anyMatch(bedEntity -> bedEntity
                                    .getNameBed()
                                    .equalsIgnoreCase(DOUBLE_BED))) {
                        apartEnt.addBedEntity(bedEnt);
                        entityManager.merge(bedEnt);
                        entityManager.flush();
                    }
                }
            }
        }

        //Сохранение в базу данных апартамента и адреса его (связь One-to-One)
        addressEntity.setApartmentEntity(apartmentEntity);
        addressRepository.saveAndFlush(addressEntity);

        return APARTMENT_SAVE_DONE;
    }

    public ApartmentResponceDto getApartmentInfoById(Long id){

        ApartmentEntity apartmentEntity = apartmentRepository.findById(id)
                .orElseThrow(() -> new ApartmentInfoException(APARTMENT_NOT_FOUND_DATABASE, 701));
        return apartmentMapper.mappingApartmentEntityToApartmentResponceDto(apartmentEntity);
    }
}
