CREATE TABLE if not exists apartment_info(
    id int8 primary key,
    number_beds int4,
    number_rooms int4,
    number_visitors int4,
    price_day int4,
    square_apartment int4,
    availability     varchar
        constraint apartment_info_availability_check
            check ((availability)::text = ANY
                   ((ARRAY ['Свободен'::character varying, 'Занят'::character varying])::text[])),
    conditioner      varchar
        constraint apartment_info_conditioner_check
            check ((conditioner)::text = ANY ((ARRAY ['Есть'::character varying, 'Нет'::character varying])::text[])),
    internet_free    varchar
        constraint apartment_info_internet_free_check
            check ((internet_free)::text = ANY ((ARRAY ['Есть'::character varying, 'Нет'::character varying])::text[])),
    name_apartment   varchar,
    parking          varchar
        constraint apartment_info_parking_check
            check ((parking)::text = ANY ((ARRAY ['Есть'::character varying, 'Нет'::character varying])::text[])),
    pet_animal       varchar
        constraint apartment_info_pet_animal_check
            check ((pet_animal)::text = ANY
                   ((ARRAY ['Можно'::character varying, 'Нельзя'::character varying])::text[])),
    smoking          varchar
        constraint apartment_info_smoking_check
            check ((smoking)::text = ANY ((ARRAY ['Можно'::character varying, 'Нельзя'::character varying])::text[])),
    telephone varchar
);

CREATE SEQUENCE apatrment_sequence start 2 increment 1;

INSERT INTO apartment_info (id, number_beds, number_rooms, number_visitors, price_day, square_apartment, availability, conditioner, internet_free, name_apartment, parking, pet_animal, smoking, telephone)
values (1,1,1,1,2000,40,'Свободен','Есть','Нет','bella','Есть','Нельзя','Можно','12563');