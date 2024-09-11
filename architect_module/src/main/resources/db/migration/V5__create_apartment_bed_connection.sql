create table if not exists apartment_bed_connection(

    apartment_id int8 references apartment_info(id),
    bed_id int8 references bed_info(id),
    primary key (apartment_id, bed_id)
);