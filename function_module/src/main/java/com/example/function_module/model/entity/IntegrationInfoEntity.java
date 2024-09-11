package com.example.function_module.model.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "integration_info")
public class IntegrationInfoEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "path_info")
    private String pathInfo;

    @Column(name = "key_info")
    private String keyInfo;

}
