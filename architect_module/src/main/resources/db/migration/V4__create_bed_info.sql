create table if not exists bed_info(

    id int8 primary key,
    name_bed varchar unique

);

create sequence bed_sequence start 1 increment 1;





