INSERT INTO hospital_management.department_sectors
    (department_id, sector_id)
SELECT
    (SELECT id FROM hospital_management.departments WHERE name = 'Unidade Assistencial, Apoio, Diagnóstico e Terapêutica' LIMIT 1),
    (SELECT id FROM hospital_management.sectors WHERE name = 'Raio-X' LIMIT 1);

INSERT INTO hospital_management.department_sectors
    (department_id, sector_id)
SELECT
    (SELECT id FROM hospital_management.departments WHERE name = 'Unidade Assistencial, Apoio, Diagnóstico e Terapêutica' LIMIT 1),
    (SELECT id FROM hospital_management.sectors WHERE name = 'Ultrassonografia' LIMIT 1);
