CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,

    title VARCHAR(255),
    author VARCHAR(255),
    year INT,
    price DOUBLE PRECISION,
    genre VARCHAR(50),
    pages INT,

    size DOUBLE PRECISION,
    cover VARCHAR(100),
    duration INT,
    narrator VARCHAR(255),
    condition VARCHAR(100),
    discount DOUBLE PRECISION
);