package com.example.function_module.controller;

import com.example.function_module.controller.controller_consts.ControllerConsts;
import com.example.function_module.model.dto.AuthUserAuthorisationDto;
import com.example.function_module.model.dto.UserInfoDto;
import com.example.function_module.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthUserInfoController {


    private Logger log = LoggerFactory.getLogger(AuthUserInfoController.class);

    private final UserInfoService userInfoService;


    @PostMapping(ControllerConsts.REGISTRATION_USER)
    public String registrUser(@RequestBody UserInfoDto userInfoDto) {
        log.info("Регистрация нового пользователя");
        log.error("Ошибка регистрации нового пользователя");
        log.warn("Предупреждение");

        return userInfoService.saveUserInfo(userInfoDto);
    }

    @PostMapping(ControllerConsts.AUTHORISATION_USER)
    public String authRegistration(@RequestBody AuthUserAuthorisationDto authUserAuthorisationDto){

       return userInfoService.findUserInfo(authUserAuthorisationDto);

    }


}
