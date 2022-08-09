CREATE TABLE product
(
    id          bigint,
    name        varchar,
    price       decimal,
    CONSTRAINT product_pk
        PRIMARY KEY (id)
);