create table carcase(
  id serial primary key,
  type varchar(200)
);

create table engine(
  id serial primary key,
  type varchar(200)
);

create table transmission(
  id serial primary key,
  type varchar(200)
);

create table car(
  id serial primary key,
  name varchar(200),
  carcase_id int references carcase(id),
  engine_id int references engine(id),
  transmission_id int references transmission(id)
);

insert into carcase(type) values('Кузов1'), ('Кузов2'), ('Кузов3'), ('Кузов4'), ('Кузов5');

insert into engine(type) values('Двигатель1'), ('Двигатель2'), ('Двигатель3'), ('Двигатель4');

insert into transmission(type) values('АККП1'), ('АККП2'), ('АККП3'), ('МККП1'), ('МККП2');

insert into car(name, carcase_id, engine_id, transmission_id) values('Toyota Corolla', 1, 1, null);
insert into car(name, carcase_id, engine_id, transmission_id) values('Toyota Corolla', null, 4, 5);
insert into car(name, carcase_id, engine_id, transmission_id) values('Toyota Camry', 4, 4, 3);
insert into car(name, carcase_id, engine_id, transmission_id) values('Toyota Camry', 4, null, null);
insert into car(name, carcase_id, engine_id, transmission_id) values('Toyota Supra', 3, null, 5);
insert into car(name, carcase_id, engine_id, transmission_id) values('Toyota Land Cruiser', 5, 4, 2);
insert into car(name, carcase_id, engine_id, transmission_id) values('BMW', null, null, null);

-- Список всех машин и привязанные к ним детали
select car.name as Название_машины, carcase.type as Тип_кузова, engine.type as Тип_двигателя, transmission.type as Тип_кп
from car left join carcase
on car.carcase_id = carcase.id
left join engine
on car.engine_id = engine.id
left join transmission
on car.transmission_id = transmission.id;

-- Кузова, которые не используются
select carcase.id as ID, carcase.type as carcase_type from carcase left join car
on carcase.id = car.carcase_id
where car.id is null;

-- Двигатели, которые не используются
select engine.id as ID, engine.type as Engine_type 
from engine left join car
on engine.id = car.engine_id
where car.name is null;
-- Коробки передач, которые не используются
select transmission.id as ID, transmission.type as Tr_type
from transmission left join car
on transmission.id = car.transmission_id
where car.name is null;