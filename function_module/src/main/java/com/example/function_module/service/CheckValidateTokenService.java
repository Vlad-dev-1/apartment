package com.example.function_module.service;

import com.example.function_module.model.entity.UserInfoEntity;

public interface CheckValidateTokenService {

    UserInfoEntity checkToken(String token);
}
