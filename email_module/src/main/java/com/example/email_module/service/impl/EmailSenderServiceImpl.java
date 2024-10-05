package com.example.email_module.service.impl;

import com.example.email_module.model.MessageForSendDto;
import com.example.email_module.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendEmail(MessageForSendDto messageForSendDto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject(messageForSendDto.getSubject());
        simpleMailMessage.setTo(messageForSendDto.getSendTo());
        simpleMailMessage.setText(messageForSendDto.getText());
        simpleMailMessage.setFrom(username);

        javaMailSender.send(simpleMailMessage);

    }
}
