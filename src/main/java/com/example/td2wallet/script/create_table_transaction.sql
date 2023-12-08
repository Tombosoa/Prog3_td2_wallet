CREATE TABLE IF NOT EXISTS "transaction" (id SERIAL PRIMARY KEY, transaction_date timestamp with timezone default currenttimestamp, type varchar(255) check(type='debit'or type='credit'), amount numeric,label varchar (255), account_id int references account(id));
INSERT INTO "transaction" (transaction_date, type, amount,label, account_id)
SELECT '2023-12-01 07:06:22'::timestamp, 'credit', 1000.1,'pret bancaire' ,1
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-01 07:06:22'::timestamp
      AND type = 'credit'
      AND amount = 1000.1
      AND label = 'pret bancaire'
      AND account_id = 1
);

INSERT INTO "transaction" (transaction_date, type, amount,label, account_id)
SELECT '2023-12-02 12:01:13'::timestamp, 'debit', -500,'pret bancaire', 2
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-02 12:01:13'::timestamp
      AND type = 'debit'
      AND amount = -500
      AND label = 'pret bancaire'
      AND account_id = 2
);

INSERT INTO "transaction" (transaction_date, type, amount,label, account_id)
SELECT '2023-12-03 05:22:13'::timestamp, 'debit', -600,'pret bancaire', 3
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-03 05:22:13'::timestamp
      AND type = 'debit'
      AND amount = -600
      AND label = 'pret bancaire'
      AND account_id = 3
);