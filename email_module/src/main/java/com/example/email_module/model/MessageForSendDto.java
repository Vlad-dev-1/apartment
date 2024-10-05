package com.example.email_module.model;

import lombok.Data;

@Data
public class MessageForSendDto {

    private String subject;

    private String text;

    private String sendTo;


}
