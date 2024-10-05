package com.example.email_module.service;

import com.example.email_module.model.MessageForSendDto;

public interface EmailSenderService {

    public void sendEmail(MessageForSendDto messageForSendDto);
}
