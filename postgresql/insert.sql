insert into users(name, role_id) values('Fedor', 2);
insert into users(name, role_id) values('Ivan', 1);
insert into role(role_name) values('admin');
insert into role(role_name) values('software engineer');
insert into rules(rules_name) values('full access');
insert into rules(rules_name) values('not full access');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(2, 2);
insert into item(item_name, user_id, category_id, state_id) values('chair', 1, 1, 1);
insert into comments(comment_text, item_id) values('Office chair', 1);
insert into attachs(attach_name, item_id) values('example.txt', 1);
insert into state(state_name) values('being processed');
insert into category(category_name) values('office furniture');

