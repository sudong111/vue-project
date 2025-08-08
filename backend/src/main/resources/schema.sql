CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(500) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS guitar_categories
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS  guitar_subtypes (
    id SERIAL PRIMARY KEY,
    category_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES guitar_categories(id)
);

CREATE TABLE IF NOT EXISTS guitar (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    subtype_id INT NOT NULL,
    brand VARCHAR(100),
    price INT NOT NULL,
    image_url TEXT,
    description TEXT,
    stock INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (subtype_id) REFERENCES guitar_subtypes(id)
);