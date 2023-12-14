create table currencyvalue(id serial primary key, id_currency_source int references currency(id), id_currency_destination int references currency(id), amount double precision,release_date date);
insert into currencyvalue (id_currency_source , id_currency_destination, amount,release_date) values (2, 1, 4500, '2023-12-03');
insert into currencyvalue (id_currency_source , id_currency_destination, amount,release_date) values (2, 1, 4600, '2023-12-08');
alter table currencyvalue alter column release_date type timestamp without time zone;
alter table currencyvalue alter column release_date set default current_timestamp;