package com.example.function_module.jdbc_dao.impl;

import com.example.function_module.jdbc_dao.JdbcDao;
import com.example.function_module.service.impl.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class JdbcDaoImpl implements JdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public static final String QUERY_FOR_SAVE_NEW_USER = "INSERT INTO user_info (id,login, user_name, password, token) values (10,'fsfsd','%s','test', 'gewgeg')";


    @Override
    @Transactional
    public String getJdbcDaoForNikName(String nikName) {

        jdbcTemplate.execute(String.format(QUERY_FOR_SAVE_NEW_USER,nikName));

        return UserInfoServiceImpl.USER_SAVE_DONE;
    }
}
