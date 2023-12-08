CREATE TABLE IF NOT EXISTS currency (id SERIAL PRIMARY KEY, name varchar(255) check (name='Euro'or name='Ariary'), code varchar(255) check (code='EUR' OR code='MGA'));
INSERT INTO currency (name, code)
SELECT 'Euro', 'EUR'
    WHERE NOT EXISTS (
    SELECT 1 FROM currency
    WHERE name = 'Euro'
);

INSERT INTO currency (name, code)
SELECT 'Ariary', 'MGA'
    WHERE NOT EXISTS (
    SELECT 1 FROM currency
    WHERE name = 'Ariary'
);