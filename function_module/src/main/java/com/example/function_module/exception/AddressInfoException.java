package com.example.function_module.exception;

import lombok.Getter;

@Getter
public class AddressInfoException extends RuntimeException{

    public static final String CITY_BY_APARTMENT_NOT_FOUND = "Значение cityApartment пустое";

    public static final String RESULT_DTO_IS_EMPTY = "Объект List<ResultDto> пустой";

    private int code;

    public AddressInfoException(String message, int code) {
        super(message);
        this.code = code;
    }
}
