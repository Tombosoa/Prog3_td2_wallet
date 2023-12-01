CREATE TABLE IF NOT EXISTS "user"(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username varchar(255),
    email varchar(255),
    password varchar(255)
);