CREATE TABLE IF NOT EXISTS "transaction" (id SERIAL PRIMARY KEY, transaction_date date, transaction_type varchar(255), transaction_price int, account_id int references account(id));
INSERT INTO "transaction" (transaction_date, transaction_type, transaction_price, account_id)
SELECT '2023-12-01'::date, 'Dépôt', 1000, 2
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-01'::date
      AND transaction_type = 'Dépôt'
      AND transaction_price = 1000
      AND account_id = 2
);

INSERT INTO "transaction" (transaction_date, transaction_type, transaction_price, account_id)
SELECT '2023-12-02'::date, 'Retrait', -500, 6
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-02'::date
      AND transaction_type = 'Retrait'
      AND transaction_price = -500
      AND account_id = 6
);

INSERT INTO "transaction" (transaction_date, transaction_type, transaction_price, account_id)
SELECT '2023-12-03'::date, 'Dépôt', 800, 7
    WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE transaction_date = '2023-12-03'::date
      AND transaction_type = 'Dépôt'
      AND transaction_price = 800
      AND account_id = 7
);