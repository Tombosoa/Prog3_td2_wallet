CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY, account_name varchar(255), user_id uuid references "user"(id), currency_id int references currency(id),type varchar (200),solde double precision default 0.0);
INSERT INTO account (account_name, user_id, currency_id, type, solde)
SELECT 'BOA', '218fd261-474a-4d49-b66a-6b28f65e5acc', 1, 'Banque', 10000
    WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE account_name = 'BOA'
      AND user_id = '218fd261-474a-4d49-b66a-6b28f65e5acc'
      AND currency_id = 1
      AND type = 'Banque'
      AND solde = 10000
);
INSERT INTO account (account_name, user_id, currency_id, type, solde)
SELECT 'BMOI', '98be1e09-62b6-48b6-a9c3-bb6019a96709', 2, 'Banque', 20000
    WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE account_name = 'BMOI'
      AND user_id = '98be1e09-62b6-48b6-a9c3-bb6019a96709'
      AND currency_id = 2
      AND type = 'Banque'
      AND solde = 20000
);
INSERT INTO account (account_name, user_id, currency_id, type, solde)
SELECT 'Orange money', 'd4bfd8d9-bb39-427b-9977-b92d0b3e8db0', 6, 'Mobile Money', 600
    WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE account_name = 'Orange money'
      AND user_id = 'd4bfd8d9-bb39-427b-9977-b92d0b3e8db0'
      AND currency_id = 6
      AND type = 'Mobile Money'
      AND solde = 600
);
