CREATE TABLE IF NOT EXISTS "user"(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username varchar(255),
    email varchar(255),
    password varchar(255)
);
INSERT INTO "user" (username, email, password)
SELECT 'john', 'john@gmail.com', '12345'
    WHERE NOT EXISTS (
    SELECT 1 FROM "user"
    WHERE username = 'john' AND email = 'john@gmail.com' AND password = '12345'
);

INSERT INTO "user" (username, email, password)
SELECT 'jean', 'jean@gmail.com', '233456'
    WHERE NOT EXISTS (
    SELECT 1 FROM "user"
    WHERE username = 'jean' AND email = 'jean@gmail.com' AND password = '233456'
);

INSERT INTO "user" (username, email, password)
SELECT 'Bella', 'Bella@gmail.com', '22222'
    WHERE NOT EXISTS (
    SELECT 1 FROM "user"
    WHERE username = 'Bella' AND email = 'Bella@gmail.com' AND password = '22222'
);


