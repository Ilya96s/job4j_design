create table users(
	id serial primary key,
	user_name varchar(200),
	role_id int references role(id)
);

create table role(
	id serial primary key,
	role_name varchar(200)
);

create table rules(
	id serial primary key,
	rules_name varchar(200)
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rules(id)
);

create table item(
	id serial primary key,
	item_name varchar(200),
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments(
	id serial primary key,
	comment_text varchar(250),
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	attach_name varchar(50),
	item_id int references item(id)
);

create table state(
	id serial primary key,
	state_name varchar(50)
);

create table categoty(
	id serial primary key,
	category_name varchar(200)
);