package com.example.function_module.exception;


import lombok.Getter;

@Getter
public class UserInfoException extends RuntimeException{

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "Данный пользователь не существует в системе";

    public static final String NOT_VALID_PASSWORD_MESSAGE = "Не верный пароль";

    public static final String USER_NAME_FIND_DATABASE = "Пользователь с таким именем уже есть в базе данных";

    public static final String USER_LOGIN_FIND_DATABASE = "Пользователь с таким логином уже есть в базе данных";

    private int code;

    public UserInfoException(String message, int code) {
        super(message);
        this.code = code;
    }


}
