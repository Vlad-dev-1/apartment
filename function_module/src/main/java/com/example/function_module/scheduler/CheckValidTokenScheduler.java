package com.example.function_module.scheduler;

import com.example.function_module.model.entity.UserInfoEntity;
import com.example.function_module.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class CheckValidTokenScheduler {

    private final UserInfoRepository userInfoRepository;


    @Scheduled(fixedDelay = 120000L)
    private void checkValidToken(){

        log.info("Планировщик начал свою работу, время старта {}", LocalDateTime.now());

        List<UserInfoEntity> infoEntityList = userInfoRepository.findUserInfoEntitiesByTokenIsNotNull().orElse(null);

        if(isNull(infoEntityList) || infoEntityList.isEmpty()){
            log.info("Текущих сессий не обнаружено");
        }else {
            infoEntityList.stream().forEach(u -> updateTokenCondition(u,parseTokenDate(u.getToken())));
        }
    }

    private void updateTokenCondition(UserInfoEntity userEntity, LocalDateTime tokenDateTime){

        if(LocalDateTime.now().isAfter(tokenDateTime)){
            userEntity.setToken(null);
            userInfoRepository.save(userEntity);
            log.info("У пользователя {} сессия закончилась, токен был удален", userEntity.getNikName());
        }
    }

    private LocalDateTime parseTokenDate(String token){

        //a01a7837-e93c-4ad3-88f7-52704260ca10|2024-08-30T17:05:28.742785500

        int indexOf = token.indexOf("|") + 1;
        String substring = token.substring(indexOf);
        LocalDateTime localDateTime = LocalDateTime.parse(substring);

        return localDateTime;
    }
}
