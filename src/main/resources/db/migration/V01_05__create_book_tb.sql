CREATE TABLE book_tb (
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher_id INTEGER NOT NULL,
    release_year INT NOT NULL,
    amount INT DEFAULT 0,
    pending_quantity INT DEFAULT 0,
    total_rented INT DEFAULT 0,
    CONSTRAINT books_tb_pk PRIMARY KEY(id),
    CONSTRAINT publisher_id_fk FOREIGN KEY (publisher_id) REFERENCES publisher_tb(id)
);