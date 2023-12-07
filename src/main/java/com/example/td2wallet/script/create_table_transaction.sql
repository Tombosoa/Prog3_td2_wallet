CREATE TABLE IF NOT EXISTS "transaction" (id SERIAL PRIMARY KEY, transaction_date date, type varchar(255) check(type='debit'or type='credit'), amount numeric,label varchar (255), account_id int references account(id));
INSERT INTO "transaction" (transaction_date, type, amount,label, account_id)
SELECT '2023-12-01'::date, 'credit', 1000.1,'pret bancaire' ,2
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-01'::date
      AND type = 'credit'
      AND amount = 1000.1
      AND label = 'pret bancaire'
      AND account_id = 2
);

INSERT INTO "transaction" (transaction_date, type, amount,label, account_id)
SELECT '2023-12-02'::date, 'debit', -500,'pret bancaire', 6
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-02'::date
      AND type = 'debit'
      AND amount = -500
      AND label = 'pret bancaire'
      AND account_id = 6
);

INSERT INTO "transaction" (transaction_date, type, amount,label, account_id)
SELECT '2023-12-03'::date, 'debit', -600,'pret bancaire', 3
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-03'::date
      AND type = 'debit'
      AND amount = -600
      AND label = 'pret bancaire'
      AND account_id = 3
);