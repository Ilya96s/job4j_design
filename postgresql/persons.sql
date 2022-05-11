create table persons(
	id serial primary key,
	names text,
	age integer,
	gender char(1),
	growth double_precision
);
insert into persons(names, age, gender, growth) values('Ivan', 30, 'M', 186.4);
select * from persons;
update persons set names = 'Fedor';
select * from persons;
delete from persons;
select * from persons;