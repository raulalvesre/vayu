SELECT * FROM category WHERE active ORDER BY id;

SELECT * FROM subcategory WHERE active ORDER BY id;

SELECT * FROM course WHERE visible;

SELECT name FROM subcategory WHERE description IS NULL OR TRIM(description) = '';