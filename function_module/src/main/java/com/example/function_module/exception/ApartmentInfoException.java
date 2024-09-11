package com.example.function_module.exception;

import lombok.Getter;

@Getter
public class ApartmentInfoException extends RuntimeException{

    public static final String APARTMENT_NOT_FOUND_DATABASE = "Такого апартамента нет в базе!";

    public static final String APARTMENT_NOT_FOUND_DATABASE_LOCATION_CLIENT = "В базе данных нет апартамента(ов) по данным местонахождения клиента";

    public static final String APARTMENT_REQUIRED_AUTHORIZATION = "Для регистрации апартамента требуется авторизация";

    public static final String APARTMENT_FOUND_ADDRESS = "Апартаменты с таким адресом уже существуют";

    private int code;

    public ApartmentInfoException(String message, int code) {
        super(message);
        this.code = code;
    }
}
