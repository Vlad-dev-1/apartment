package com.example.function_module.service.impl;

import com.example.function_module.exception.UserInfoException;
import com.example.function_module.jdbc_dao.JdbcDao;
import com.example.function_module.mapper.UserInfoMapper;
import com.example.function_module.model.dto.AuthUserAuthorisationDto;
import com.example.function_module.model.dto.UserInfoDto;
import com.example.function_module.model.entity.UserInfoEntity;
import com.example.function_module.repository.UserInfoRepository;
import com.example.function_module.service.UserInfoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.function_module.exception.UserInfoException.*;
import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    private final UserInfoMapper userInfoMapper;

    private final JdbcDao jdbcDao;

    private final EntityManager entityManager;

    public static final String USER_SAVE_DONE = "Пользователь сохранен";

    @Override
    @Transactional
    public String saveUserInfo(UserInfoDto userInfoDto) {

        try {
            //Проверка наличия пользователя по имени в базе данных
            UserInfoEntity userInfoEntityByNikName = criteriaQueryForNikname(userInfoDto.getNikName());
//        UserInfoEntity userInfoEntityByNikName = userInfoRepository.findUserInfoEntityByNikNameNative(userInfoDto.getNikName());
            if (!isNull(userInfoEntityByNikName)) {
                throw new UserInfoException(USER_NAME_FIND_DATABASE, 700);
            }
            //Проверка наличия пользователя по логину в базе данных
            UserInfoEntity userInfoEntityByLogin = criteriaQueryForLogin(userInfoDto.getLogin());
//        UserInfoEntity userInfoEntityByLogin = userInfoRepository.findUserInfoEntityByLoginNative(userInfoDto.getLogin());
            if (!isNull(userInfoEntityByLogin)) {
                throw new UserInfoException(USER_LOGIN_FIND_DATABASE, 702);
            }
        } catch (NoResultException e) {
            log.info("Можно сохранять, такого пользователя нет в базе данных");
        }
        //Сохранение пользователя в базе данных
        userInfoRepository.save(userInfoMapper.mapperUserInfoDto(userInfoDto));
//        String jdbcDaoForNikName = jdbcDao.getJdbcDaoForNikName(userInfoDto.getNikName());
        return USER_SAVE_DONE;
    }


    @Override
    public String findUserInfo(AuthUserAuthorisationDto authUserAuthorisationDto) {

        UserInfoEntity userInfoEntity = userInfoRepository.findUserInfoEntityByLoginOptional(authUserAuthorisationDto.getLogin())
                .orElseThrow(() -> new UserInfoException(USER_NOT_FOUND_EXCEPTION_MESSAGE, 700));

        if(!authUserAuthorisationDto.getPassword().equals(userInfoEntity.getPassword())){
            throw new UserInfoException(NOT_VALID_PASSWORD_MESSAGE, 701);
        }

        String token = generatSessionToken();
        userInfoEntity.setToken(token);
        userInfoRepository.save(userInfoEntity);

        return token;
    }

    private String generatSessionToken(){
        String uniqueToken = UUID.randomUUID().toString();
        return uniqueToken + "|" + LocalDateTime.now().plusDays(1L);
    }


    private UserInfoEntity criteriaQueryForNikname(String nikName) throws NoResultException {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserInfoEntity> query = criteriaBuilder.createQuery(UserInfoEntity.class);
        Root<UserInfoEntity> root = query.from(UserInfoEntity.class);
        query.select(root)
                .where(criteriaBuilder.equal(root.get("nikName"), nikName));

        UserInfoEntity singleResult = entityManager.createQuery(query).getSingleResult();
        return singleResult;

    }

    private UserInfoEntity criteriaQueryForLogin(String login) throws NoResultException {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserInfoEntity> query = criteriaBuilder.createQuery(UserInfoEntity.class);
        Root<UserInfoEntity> root = query.from(UserInfoEntity.class);
        query.select(root)
                .where(criteriaBuilder.equal(root.get("login"), login));

        return entityManager.createQuery(query).getSingleResult();
    }


}
