create table if not exists rating_comment_apartment_info(

    id int8 primary key,
    rating_apartment smallint,
    comment_apatment varchar,
    apartment_id int8,
    foreign key (apartment_id) references apartment_info(id)
);

create sequence rating_comment_apartment_sequence start 1 increment 1;