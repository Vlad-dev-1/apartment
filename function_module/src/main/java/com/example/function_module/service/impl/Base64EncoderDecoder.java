package com.example.function_module.service.impl;


import java.util.Base64;


public class Base64EncoderDecoder {

    public static String encoder(String value){

        Base64.Encoder encoder = Base64.getEncoder();
        String encodeToString = encoder.encodeToString(value.getBytes());
        return encodeToString;
    }

    public static String decoder(String value){

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(value);
        return new String(decode);
    }
}
