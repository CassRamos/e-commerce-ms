CREATE TABLE IF NOT EXISTS category
(
    id          INTEGER      NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id                 INTEGER          NOT NULL PRIMARY KEY,
    name               VARCHAR(255)     NOT NULL,
    description        VARCHAR(255)     NOT NULL,
    available_quantity DOUBLE PRECISION NOT NULL,
    price              NUMERIC(38, 2)   NOT NULL,
    category_id        INTEGER          NOT NULL
        CONSTRAINT fk1_category_product REFERENCES category
);

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 1;