package com.example.email_module.controller;

import com.example.email_module.controller.controller_consts.ControllerConsts;
import com.example.email_module.model.MessageForSendDto;
import com.example.email_module.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailMessageController {

    private final EmailSenderService emailSenderService;

    @PostMapping(ControllerConsts.EMAIL_MESSAGE_SEND_OPERATION)
    public void sendMailMessage(@RequestBody MessageForSendDto messageForSendDto){

        emailSenderService.sendEmail(messageForSendDto);
    }
}
