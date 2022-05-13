create table persons(
	id serial primary key,
	first_name text,
	age integer,
	gender char(1),
	growth double_precision
);
insert into persons(first_name, age, gender, growth) values('Ivan', 30, 'M', 186.4);
select * from persons;
update persons set first_name = 'Fedor';
select * from persons;
delete from persons;
select * from persons;