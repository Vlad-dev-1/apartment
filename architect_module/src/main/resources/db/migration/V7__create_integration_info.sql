create table if not exists integration_info(

    id varchar primary key,
    key_info varchar,
    path_info varchar
);

INSERT INTO integration_info (id, key_info,path_info) values ('GEO','a67bf80a3bba41f5a8bd0e0e8bf4bb38','https://api.opencagedata.com/geocode/v1/json?q=%s+%s&key=%s');

