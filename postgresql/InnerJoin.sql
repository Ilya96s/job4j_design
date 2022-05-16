create table company(
	id serial primary key,
	company_name text
);

create table employee(
	id serial primary key,
	first_name text,
	last_name text,
	company_id int references company(id)
);

insert into company(company_name) values('JetBrains');
insert into company(company_name) values('Luxsoft');
insert into company(company_name) values('Yandex');

insert into employee(first_name, last_name, company_id)
values('Ivan', 'Ivanov', 1);
insert into employee(first_name, last_name, company_id)
values('Petr', 'Petrov', 1);
insert into employee(first_name, last_name, company_id)
values('Ivan', 'Morozov', 3);
insert into employee(first_name, last_name, company_id)
values('Sergey', 'Novikov', 2);

select * from employee
join company
on employee.company_id = company.id;

select emp.first_name, comp.company_name
from employee as emp
join  company as comp
on emp.company_id = comp.id;

select emp.first_name as Имя, comp.company_name as Компания
from employee as emp
join company as comp
on emp.company_id = comp.id;