package com.example.function_module.service;

import com.example.function_module.model.dto.AuthUserAuthorisationDto;
import com.example.function_module.model.dto.UserInfoDto;

public interface UserInfoService {

    String saveUserInfo(UserInfoDto userInfoDto);

    String findUserInfo(AuthUserAuthorisationDto authUserAuthorisationDto);
}
