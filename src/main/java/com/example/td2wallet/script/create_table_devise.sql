CREATE TABLE IF NOT EXISTS devise (id SERIAL PRIMARY KEY, devise_name varchar(255), devise_country varchar(255));
INSERT INTO devise (devise_name, devise_country)
SELECT 'Euro', 'Europe'
    WHERE NOT EXISTS (
    SELECT 1 FROM devise
    WHERE devise_name = 'Euro'
);

INSERT INTO devise (devise_name, devise_country)
SELECT 'Yuan', 'CHINE'
    WHERE NOT EXISTS (
    SELECT 1 FROM devise
    WHERE devise_name = 'Yuan'
);

INSERT INTO devise (devise_name, devise_country)
SELECT 'Yen', 'Japon'
    WHERE NOT EXISTS (
    SELECT 1 FROM devise
    WHERE devise_name = 'Yen'
);