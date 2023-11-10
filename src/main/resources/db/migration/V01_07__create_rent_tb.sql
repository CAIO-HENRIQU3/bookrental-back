CREATE TABLE rental_tb (
    id SERIAL NOT NULL,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    rental_date DATE NOT NULL,
    forecast_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT rental_tb_pk PRIMARY KEY(id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES user_tb(id),
    CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES book_tb(id)
);