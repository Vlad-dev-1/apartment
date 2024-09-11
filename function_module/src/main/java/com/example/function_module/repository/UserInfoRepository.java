package com.example.function_module.repository;

import com.example.function_module.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_info where token = :token")
    Optional<UserInfoEntity> findUserInfoEntityByToken(@Param("token") String token);


    Optional<List<UserInfoEntity>> findUserInfoEntitiesByTokenIsNotNull();

    UserInfoEntity findUserInfoEntityByNikName(String nikName);

    UserInfoEntity findUserInfoEntityByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT * FROM user_info where user_name = :nikName")
    UserInfoEntity findUserInfoEntityByNikNameNative(String nikName);

    @Query(nativeQuery = true, value = "SELECT * FROM user_info where login = :login")
    UserInfoEntity findUserInfoEntityByLoginNative(String login);

    @Query(nativeQuery = true, value = "SELECT * FROM user_info where login = :login")
    Optional<UserInfoEntity> findUserInfoEntityByLoginOptional(String login);

    @Query(value = "SELECT u FROM UserInfoEntity u where u.nikName = :nikName")
    UserInfoEntity findUserInfoEntityByNikNameNativeJPQL(String nikName);

    @Query(value = "SELECT u FROM UserInfoEntity u where u.login = :login")
    UserInfoEntity findUserInfoEntityByLoginNativeJPQL(String login);
}
