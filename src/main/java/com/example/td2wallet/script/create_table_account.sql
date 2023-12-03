CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY, account_name varchar(255), user_id uuid references "user"(id), devise_id int references devise(id));
INSERT INTO account (account_name, user_id, devise_id)
SELECT 'BOA', '218fd261-474a-4d49-b66a-6b28f65e5acc', '9'
    WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE account_name = 'BOA' AND user_id = '218fd261-474a-4d49-b66a-6b28f65e5acc' AND devise_id = '9'
);
INSERT INTO account (account_name, user_id, devise_id)
SELECT 'BNI', 'd4bfd8d9-bb39-427b-9977-b92d0b3e8db0', '10'
    WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE account_name = 'BNI' AND user_id = 'd4bfd8d9-bb39-427b-9977-b92d0b3e8db0' AND devise_id = '10'
);
INSERT INTO account (account_name, user_id, devise_id)
SELECT 'BFV', '98be1e09-62b6-48b6-a9c3-bb6019a96709', '10'
    WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE account_name = 'BFV' AND user_id = '98be1e09-62b6-48b6-a9c3-bb6019a96709' AND devise_id = '10'
);
