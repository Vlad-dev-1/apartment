package com.example.function_module.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentExceptionHandler {

    @ExceptionHandler(UserInfoException.class)
    public ResponseEntity<?> catchException(UserInfoException e) {

        return printExceptionsMessage(e);
    }

    @ExceptionHandler(ApartmentInfoException.class)
    public ResponseEntity<?> catchException(ApartmentInfoException e) {

        return printExceptionsMessage(e);
    }

    @ExceptionHandler(AddressInfoException.class)
    public ResponseEntity<?> catchException(AddressInfoException e) {

        return printExceptionsMessage(e);
    }

    private ResponseEntity<?> printExceptionsMessage(RuntimeException e) {

        if (e instanceof UserInfoException) {
            UserInfoException userInfoException = (UserInfoException) e;
            ((UserInfoException) e).getCode();
            return buildErrorMessage(userInfoException.getCode(), userInfoException.getMessage());
        } else if (e instanceof ApartmentInfoException) {
            ApartmentInfoException apartmentInfoException = (ApartmentInfoException) e;
            return ResponseEntity.status(apartmentInfoException.getCode()).body(apartmentInfoException.getMessage());
        } else if (e instanceof AddressInfoException) {
            AddressInfoException addressInfoException = (AddressInfoException) e;
            return ResponseEntity.status(addressInfoException.getCode()).body(addressInfoException.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> buildErrorMessage(int code, String message){

        return ResponseEntity.status(code).body(message);
    }
}
