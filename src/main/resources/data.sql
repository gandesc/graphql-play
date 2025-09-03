DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);

INSERT INTO customer(name, age, city) VALUES ('sam', 10, 'atlanta')
INSERT INTO customer(name, age, city) VALUES ('mike', 15, 'houston')
INSERT INTO customer(name, age, city) VALUES ('jame', 20, 'miami')
INSERT INTO customer(name, age, city) VALUES ('john', 30, 'las vegas')
