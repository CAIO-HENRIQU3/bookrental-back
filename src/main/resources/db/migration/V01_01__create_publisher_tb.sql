CREATE TABLE publisher_tb(
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    CONSTRAINT publisher_tb_id PRIMARY KEY(id)
);