create table departments (
  id serial primary key,
  name varchar (100)
);

create table employees (
  id serial primary key,
  name varchar (200),
  department_id int references departments(id)
 );
 
insert into departments (name) values ('Производство');
insert into departments (name) values ('Техническая дирекция');
insert into departments (name) values ('Управление по логистике');
insert into departments (name) values ('Юридический отдел');

insert into employees (name, department_id) values ('Ivan', 1);
insert into employees (name, department_id) values('Dima', 2);
insert into employees (name, department_id) values('Petr', 3);
insert into employees (name, department_id) values('Kolya' ,1);
insert into employees (name, department_id) values('Ilya', 2);
insert into employees (name, department_id) values('Tanya',3);
insert into employees (name, department_id) values('Julia', null);
insert into employees (name, department_id) values('Lena',null);
insert into employees (name, department_id) values('Vlad',2);
insert into employees (name, department_id) values('Anya', null);

-- Запросы с left соединением
select * from departments
left join employees
on departments.id = employees.department_id;

-- Запросы с right соединением
select * from departments
right join employees
on departments.id = employees.department_id;

-- Запросы с full соединением
select * from departments
full join employees
on departments.id = employees.department_id;


-- Запросы с cross соединением
select * from departments
cross join employees;

-- Используя left join найти департаменты, у которых нет работников
select * from departments
left join employees
on departments.id = employees.department_id
where employees.name is null;

-- Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select * from departments
left join employees
on departments.id = employees.department_id
where employees.name is not null;

select * from departments
right join employees
on departments.id = employees.department_id
where departments.name is not null;

insert into teens(name, gender) values('Иван', 'М');
insert into teens(name, gender) values('Мария', 'Ж');
insert into teens(name, gender) values('Алексей', 'М');
insert into teens(name, gender) values('Таня', 'Ж');
insert into teens(name, gender) values('Юлия', 'Ж');
insert into teens(name, gender) values('Настя', 'Ж');
insert into teens(name, gender) values('Влад', 'М');
insert into teens(name, gender) values('Егор', 'М');

select t1.name, t2.name from teens as t1 cross join teens as t2
where t1.gender != t2.gender;