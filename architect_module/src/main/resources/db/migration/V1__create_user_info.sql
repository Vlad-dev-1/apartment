CREATE TABLE if not exists user_info(
    id int8 primary key,
    login varchar,
    user_name varchar,
    password varchar,
    token varchar
);

CREATE SEQUENCE user_info_sequence start 2 increment 1;

INSERT INTO user_info (id,login,user_name,password,token)
values (1,'red','red','red','test');