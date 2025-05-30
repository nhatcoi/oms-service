-- Insert sample categories with fixed IDs
INSERT INTO category (id, name, description)
VALUES
    (1, 'Electronics', 'Electronic devices and gadgets'),
    (2, 'Books', 'Printed and electronic books'),
    (3, 'Clothing', 'Apparel and accessories');

-- Insert sample products using matching category_id
INSERT INTO product (id, name, description, price, available_quantity, category_id)
VALUES
    (1, 'Smartphone', 'Latest Android smartphone', 699.99, 50, 1),
    (2, 'Laptop', 'Lightweight ultrabook', 1299.50, 30, 1),
    (3, 'Novel', 'Bestselling fiction book', 19.99, 100, 2),
    (4, 'T-Shirt', '100% cotton T-shirt', 14.95, 200, 3);

-- Reset sequences to continue correctly after manual insert
SELECT setval('category_seq', 51, false);
SELECT setval('product_seq', 51, false);