CREATE TABLE materials
(
    id            BIGINT       NOT NULL,
    name          VARCHAR(255) NOT NULL,
    abbreviations VARCHAR(255),
    images        VARCHAR(255),
    type          VARCHAR(100),

    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id         BIGINT       NOT NULL,
    barcode    VARCHAR(20)  NOT NULL,
    brand      VARCHAR(200) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    type       VARCHAR(100),
    image_link VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE components
(
    id         BIGINT       NOT NULL,
    product_id BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);