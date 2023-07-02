--1. Создать таблицу клиента с полями:
--● Фамилия
--● Имя
--● Дата рождения
--● Телефон
--● Почта
--● Список названий книг из библиотеки [“name”, “name”, …]
create table users
(
    last_name   varchar(20)             not null,
    first_name  varchar(20)             not null,
    birth_date  date                    not null,
    phone       varchar(15),
    mail        varchar(20) primary key not null,
    book_titles text[]
);

select *
from users;



create table books
(
    id         serial primary key not null,
    title      varchar(100)       not null,
    author     varchar(100)       not null,
    date_added timestamp          not null
);

insert into books(title, author, date_added)
values ('Недоросль', 'Д. И. Фонвизин', now() - interval '276h'),
       ('Путешествие из Петербурга в Москву', 'А. Н. Радищев', now() - interval '167h'),
       ('Доктор Живаго', 'Б. Л. Пастернак', now() - interval '93h'),
       ('Сестра моя - жизнь', 'Б. Л. Пастернак', now() - interval '61h'),
       ('Недоросль', 'Автор', now() - interval '60h');

select *
from books;
