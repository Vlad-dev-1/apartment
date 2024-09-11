package com.example.function_module.service.impl;

import com.example.function_module.exception.ApartmentInfoException;
import com.example.function_module.model.entity.UserInfoEntity;
import com.example.function_module.repository.UserInfoRepository;
import com.example.function_module.service.CheckValidateTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.function_module.exception.ApartmentInfoException.APARTMENT_REQUIRED_AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class CheckValidateTokenServiceImpl implements CheckValidateTokenService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserInfoEntity checkToken(String token) {

//        List<UserInfoEntity> userInfoEntitiesByTokenIsNotNull = userInfoRepository.findUserInfoEntitiesByTokenIsNotNull();

        UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoEntityByToken(token)
                .orElseThrow(() -> new ApartmentInfoException(APARTMENT_REQUIRED_AUTHORIZATION, 705));


        return userInfoEntity;
    }
}
