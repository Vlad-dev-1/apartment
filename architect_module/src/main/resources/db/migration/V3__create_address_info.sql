create table if not exists address_info
(
    id           int8 primary key,
    house_number int4,
    number_flat  int4,
    postcode     int4,
    apartment_id int8,
    foreign key (apartment_id) references apartment_info(id),
    city         varchar,
    country      varchar,
    region       varchar,
    street       varchar
);

CREATE SEQUENCE address_apartment_sequence start 2 increment 1;

INSERT INTO address_info (id, house_number, number_flat, postcode, apartment_id, city, country, region, street)
values (1,1,1,1,1,'Москва','Russia','Московская область','street');