INSERT INTO hospital_management.hospital_departments (hospital_id, department_id)
SELECT (SELECT id FROM hospital_management.hospitals ORDER BY created_at LIMIT 1),
(SELECT id FROM hospital_management.departments ORDER BY created_at LIMIT 1)
WHERE (SELECT count(*) FROM hospital_management.hospital_departments hd2) = 0;

