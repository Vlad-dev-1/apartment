package com.example.function_module.model.entity.embedded_apartment;

import com.example.function_module.model.entity.enum_apartment.*;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specifications {

    @Column(name = "internet_free")
    @Enumerated(value = EnumType.STRING)
    private InternetFree internetFree;

    @Column(name = "parking")
    @Enumerated(value = EnumType.STRING)
    private Parking parking;

    @Column(name = "conditioner")
    @Enumerated(value = EnumType.STRING)
    private Conditioner conditioner;

    @Column(name = "pet_animal")
    @Enumerated(value = EnumType.STRING)
    private PetAnimal petAnimal;

    @Column(name = "smoking")
    @Enumerated(value = EnumType.STRING)
    private Smoking smoking;

    @Column(name = "number_visitors")
    private Integer numberVisitors;

    @Column(name = "number_beds")
    private Integer numberBeds;
}
