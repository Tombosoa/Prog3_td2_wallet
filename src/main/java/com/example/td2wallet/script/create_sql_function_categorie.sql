CREATE OR REPLACE FUNCTION getnewtotaltransac(
    p_account_id INT,
    p_start_date TIMESTAMP WITH TIME ZONE,
    p_end_date TIMESTAMP WITH TIME ZONE
)
RETURNS TABLE (category_name category_enum, total_amount bigint)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        c.category_name,
        COALESCE(SUM(t.amount), 0) AS total_amount
    FROM
        transaction t
    LEFT JOIN
        category c ON t.category_id = c.id
    WHERE
        t.transaction_date BETWEEN p_start_date AND p_end_date
        AND t.account_id = p_account_id
    GROUP BY
        c.category_name;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM getnewtotaltransac(1, '2023-12-07 01:20:59.979769-05', '2023-12-15 01:20:59.979769-05');