CREATE TYPE category_enum AS ENUM(
    'Food and Drinks',
    'Online Shopping',
    'Housing',
    'Transportation',
    'Vehicle',
    'Leisure',
    'Multimedia and Computers',
    'Financial Expenses',
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
CREATE TABLE IF NOT EXISTS category (id SERIAL PRIMARY KEY,type type_enum,category_name category_enum,subcategory_id int  references subcategory(id));
INSERT INTO category ( type, category_name,subcategory_id)
SELECT 'Outgoing','Food and Drinks',1
    WHERE NOT EXISTS (
    SELECT 1 FROM category
    WHERE type = 'Outgoing'
     AND category_name = 'Food and Drinks'
     AND subcategory_id= 1
);
INSERT INTO category ( type, category_name,subcategory_id)
SELECT 'Incoming','Income',2
    WHERE NOT EXISTS (
    SELECT 1 FROM category
    WHERE type = 'Incoming'
     AND category_name = 'Income'
     AND subcategory_id= 2
);
INSERT INTO category ( type, category_name,subcategory_id)
SELECT 'Incoming','Income',3
    WHERE NOT EXISTS (
    SELECT 1 FROM category
    WHERE type = 'Incoming'
     AND category_name = 'Income'
     AND subcategory_id= 3
);