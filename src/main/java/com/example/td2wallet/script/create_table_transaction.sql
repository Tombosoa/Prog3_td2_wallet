CREATE TABLE IF NOT EXISTS "transaction" ( id SERIAL PRIMARY KEY,transaction_date timestamp with time zone DEFAULT current_timestamp, type varchar(255) CHECK(type='debit' OR type='credit'), amount numeric, label varchar(255),  account_id int REFERENCES account(id), category_id int REFERENCES category(id),subcategory_id int REFERENCES subcategory(id));
INSERT INTO "transaction" (transaction_date, type, amount,label, account_id,category_id,subcategory_id)
SELECT '2023-12-01 07:06:22'::timestamp, 'debit', -500,'Fast_Food_Delight' ,1,1,1
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-01 07:06:22'::timestamp
      AND type = 'debit'
      AND amount = -500
      AND label = 'Fast_Food_Delight'
      AND account_id = 1
      AND category_id = 1
      AND subcategory_id = 1
);

INSERT INTO "transaction" (transaction_date, type, amount,label, account_id,category_id,subcategory_id)
SELECT '2023-12-02 12:01:13'::timestamp, 'credit', 1500,'Monthly_Salary', 2,2,2
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-02 12:01:13'::timestamp
      AND type = 'credit'
      AND amount = 1500
      AND label = 'Monthly_Salary'
      AND account_id = 2
      AND category_id = 2
      AND subcategory_id = 2
);

INSERT INTO "transaction" (transaction_date, type, amount,label, account_id,category_id,subcategory_id)
SELECT '2023-12-03 05:22:13'::timestamp, 'credit', 600,'Lottery_winnings', 3,3,3
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-03 05:22:13'::timestamp
      AND type = 'credit'
      AND amount = 600
      AND label = 'Lottery_winnings'
      AND account_id = 3
      AND category_id = 3
);

alter table transaction add column category_id int references category(id);
alter table transaction add column subcategory_id int references subcategory(id);
