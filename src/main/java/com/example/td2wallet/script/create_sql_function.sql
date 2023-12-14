CREATE OR REPLACE FUNCTION gettotaltransac(p_account_id INT, p_start_date TIMESTAMP WITH TIME ZONE, p_end_date TIMESTAMP WITH TIME ZONE)
RETURNS TABLE (credit NUMERIC, debit NUMERIC, account_id INT)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        SUM(CASE WHEN type = 'Credit' THEN amount ELSE 0::NUMERIC END) AS credit,
        SUM(CASE WHEN type = 'Debit' THEN amount ELSE 0::NUMERIC END) AS debit,
        t.account_id
    FROM
        transaction t
    WHERE
        t.account_id = p_account_id
        AND t.transaction_date BETWEEN p_start_date AND p_end_date
    GROUP BY
        t.account_id;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM gettotaltransac(1, '2023-12-07 01:20:59.979769-05', '2023-12-09 01:20:59.979769-05');