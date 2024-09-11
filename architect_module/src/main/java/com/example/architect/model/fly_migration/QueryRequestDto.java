package com.example.architect.model.fly_migration;

import lombok.Data;

import java.util.HashMap;


@Data
public class QueryRequestDto {

    private String operationType;

    private String tableName;

    private HashMap<String, String> queryValue;

}
