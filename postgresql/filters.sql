create table type(
	id serial primary key,
	name varchar(50)
);

create table product(
	id serial primary key,
	name varchar(50),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name)
values('Молоко'), ('Сыр'), ('Мороженое');

insert into product(name, type_id, expired_date, price)
values('Молоко деревенское', 1, date '2022.05.25', 85), ('Молоко магазинное', 1, date '2022.05.29', 60), ('Домик в деревне', 1, date '2022.05.23', 95);
insert into product(name, type_id, expired_date, price)
values('Сыр алтайский', 2, date '2022.07.24', 150), ('Сыр домашний', 2, date '2022.06.17', 150), ('Чеддер', 2, date '2022.06.04', 250);
insert into product(name, type_id, expired_date, price)
values('Мороженое вкусное', 3, date '2022.08.25', 45), ('Ванильное мороженое', 3, date '2022.07.21', 78.5), ('Клубничное мороженое', 3, date '2022.07.05', 66.85);

-- 1. Запрос на  получение всех продуктов с типом "СЫР"
select product.name
from product 
join type
on product.type_id = type.id
where type.name = 'Сыр';

-- 2. Запрос на получения всех продуктов, у кого в имени есть слово "мороженое"
select product.name
from product
where product.name like '%мороженое%';

-- 3. Запрос, который выводит все продукты, срок годности которых уже истек
select product.name
from product
where product.expired_date < current_date;

-- 4. Запрос, который выводит самый дорогой продукт.
select product.name, product.price 
from product
where product.price = (select max(price) from product);

-- 5. Запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select type.name, count(product.name)
from type 
join product
on type.id = product.type_id
group by type.name;

-- 6. Запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select product.name
from product
join type
on product.type_id = type.id
where type.name = 'Сыр'
or type.name = 'Молоко';

-- 7. Запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2. 
select type.name, count(product.name)
from type 
join product
on type.id = product.type_id
group by type.name
having count(product.name) < 10;

-- 8. Вывести все продукты и их тип.
select product.name, type.name
from product
join type
on product.type_id = type.id;
