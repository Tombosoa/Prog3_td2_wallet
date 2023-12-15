CREATE TYPE category_enum AS ENUM(
    'Food_and_Drinks',
    'Online_Shopping',
    'Housing',
    'Transportation',
    'Vehicle',
    'Leisure',
    'Multimedia_and_Computers',
    'Financial_Expenses',
    'Investments',
    'Income',
    'Other',
    'Unknown'
);
CREATE TYPE type_enum AS ENUM(
    'Incoming',
    'Outgoing',
    'Loan'

);
CREATE TABLE IF NOT EXISTS category (id SERIAL PRIMARY KEY,type type_enum,category_name category_enum);
INSERT INTO category ( type, category_name)
SELECT 'Outgoing','Food and Drinks'
    WHERE NOT EXISTS (
    SELECT 1 FROM category
    WHERE type = 'Outgoing'
     AND category_name = 'Food and Drinks'
);
INSERT INTO category ( type, category_name)
SELECT 'Incoming','Income'
    WHERE NOT EXISTS (
    SELECT 1 FROM category
    WHERE type = 'Incoming'
     AND category_name = 'Income'
);
INSERT INTO category ( type, category_name)
SELECT 'Incoming','Income'
    WHERE NOT EXISTS (
    SELECT 1 FROM category
    WHERE type = 'Incoming'
     AND category_name = 'Income'
);