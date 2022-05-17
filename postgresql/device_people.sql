create table devices(
	id serial primary key,
	name varchar(255),
	price float
);

create table people(
	id serial primary key,
	name varchar(255)
);

create table devices_people(
	ide serial primary key,
	device_id int references devices(id),
	people_id int references people(id)
);

insert into devices(name, price)
values('Iphone 6S', 2000), ('Iphone 7', 4500), 
('Iphone 8', 6500), ('Iphone 11', 10000), ('Ihone 12', 15000);

insert into people(name)
values('Vladimir'), ('Alexsander'), ('Ivan'), ('Maxim');

insert into devices_people(device_id, people_id)
values(1, 1), (5, 1), (2, 2), (3, 3), (2, 4), (3, 4), (4, 4);


select avg(price) from devices;

select people.name, avg(price)
from devices_people join devices
on devices_people.device_id = devices.id
join people
on devices_people.people_id = people.id
group by people.name;

select people.name, avg(price)
from devices_people join devices
on devices_people.device_id = devices.id
join people
on devices_people.people_id = people.id
group by people.name
having avg(devices.price) > 5000;