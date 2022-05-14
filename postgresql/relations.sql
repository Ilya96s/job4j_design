-- Отношение one to many;
create table footbal_club(
    id serial primary key,
    club_name varchar(128)
);

create table footbal_player(
    id serial primary key,
    player_name varchar(150),
    footbal_club_id int references footbal_club(id)
);

-- Отношение one to one;
create table driver(
    id serial primary key,
    driver_name varchar(128)
);

create table drivers_car(
    id serial primary key,
    car_brand varchar(128),
    car_color varchar(128),
    car_number varchar(128),
    drivers_id int references driver(id)
);

-- Отношение many to many;
create table articles(
    id serial primary key,
    article_name text
);

create table tags(
    id serial primary key,
    tags_name varchar(128)
);

create table article_tags(
    id serial primary key,
    tags_id int references tags(id),
    article_id int references articles(id)
);