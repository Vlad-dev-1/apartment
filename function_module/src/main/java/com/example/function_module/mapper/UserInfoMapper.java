package com.example.function_module.mapper;

import com.example.function_module.model.dto.UserInfoDto;
import com.example.function_module.model.entity.UserInfoEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserInfoMapper {

    UserInfoEntity mapperUserInfoDto(UserInfoDto userInfoDto);

    UserInfoDto mapperUserInfo(UserInfoEntity userInfo);
}
