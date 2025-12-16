CREATE TABLE product (
    id INT PRIMARY KEY,
    quantity INT NOT NULL,
    version INT
);

INSERT INTO product (id, quantity, version)
VALUES (1, 1, 0);