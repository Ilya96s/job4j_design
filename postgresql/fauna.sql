 create table fauna(
 	id serial primary key,
 	name text,
 	avg_age int,
 	discovery_date date
 );

 insert into fauna(name, avg_age, discovery_date)
 values('Tiger', 12000, date '0004.12.23');
 insert into fauna(name, avg_age, discovery_date)
 values('Clown fish', 7500, date ' 1875.04.12');
 insert into fauna(name, avg_age, discovery_date)
 values('Jewel fish', 10000, date '1790.08.01');
 insert  into fauna(name, avg_age, discovery_date)
 values('Elephant', 18000, date '1000.10.01');
 select * from fauna where name LIKE '%fish%';
 select * from fauna where avg_age >= 10000 and avg_age <= 21000;
 select * from fauna where discovery_date is null;
 select * from fauna where discovery_date < '1950.01.01';
