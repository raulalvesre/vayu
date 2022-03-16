SELECT *
FROM category
WHERE active
ORDER BY `order`;

SELECT *
FROM subcategory
WHERE active
ORDER BY `order`;

SELECT *
FROM course
WHERE visible;

SELECT name
FROM subcategory
WHERE description IS NULL
   OR TRIM(description) = '';

#ADVANCED

SELECT sb.name, sb.`order`
FROM subcategory sb
WHERE EXISTS(SELECT * FROM course c WHERE c.subcategory_id = sb.id AND sb.active)
ORDER BY sb.`order`;

SELECT instructor_name, COUNT(course.id) AS qtd_cursos
FROM course
GROUP BY instructor_name
ORDER BY qtd_cursos DESC
LIMIT 1;

SELECT ct.name, COALESCE(count(c.id), 0) as qtd_cursos, COALESCE(SUM(c.estimated_hours_to_finish), 0) as sum_hours
FROM category ct
         LEFT JOIN subcategory s on ct.id = s.category_id
         LEFT JOIN course c on s.id = c.subcategory_id
WHERE ct.active
GROUP BY ct.id;
