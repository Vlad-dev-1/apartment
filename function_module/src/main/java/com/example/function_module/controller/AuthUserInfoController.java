package com.example.function_module.controller;

import com.example.function_module.controller.controller_consts.ControllerConsts;
import com.example.function_module.model.dto.AuthUserAuthorisationDto;
import com.example.function_module.model.dto.UserInfoDto;
import com.example.function_module.service.CheckValidateTokenService;
import com.example.function_module.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthUserInfoController {

    private final UserInfoService userInfoService;


    @PostMapping(ControllerConsts.REGISTRATION_USER)
    public String registrUser(@RequestBody UserInfoDto userInfoDto) {

        return userInfoService.saveUserInfo(userInfoDto);
    }

    @PostMapping("/auth-registration")
    public String authRegistration(@RequestBody AuthUserAuthorisationDto authUserAuthorisationDto){

       return userInfoService.findUserInfo(authUserAuthorisationDto);

    }


}
