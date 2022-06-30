-- имена всех person, которые не состоят в компании с id = 5;
-- название компании для каждого человека.
select person.name "Имя сотрудника", company.name as "Название компании"
from person
join company
on person.company_id = company.id
where company.id != 5;

-- компании с максимальным количеством человек + количество человек в этой компании
select company.name as "Название компании", count(person.company_id) as "Количество сотрудников"
from company
join person
on company.id = person.company_id
group by company.name
having count(*) = (select company_id from person group by company_id order by count(company_id) desc limit 1);