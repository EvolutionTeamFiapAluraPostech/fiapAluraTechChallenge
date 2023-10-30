INSERT INTO hospital_management.sector_professions
    (deleted, version, created_at, created_by, sector_id, profession_id, total_hours)
SELECT
    false, 0, current_timestamp, null,
    (SELECT id FROM hospital_management.sectors WHERE name = 'Raio-X' LIMIT 1),
    (SELECT id FROM profession_management.professions WHERE description = 'ENFERMEIRO(A)' LIMIT 1),
    1;

INSERT INTO hospital_management.sector_professions
    (deleted, version, created_at, created_by, sector_id, profession_id, total_hours)
SELECT
    false, 0, current_timestamp, null,
    (SELECT id FROM hospital_management.sectors WHERE name = 'Raio-X' LIMIT 1),
    (SELECT id FROM profession_management.professions WHERE description = 'TÉCNICO(A) EM ENFERMAGEM' LIMIT 1),
    2;

INSERT INTO hospital_management.sector_professions
    (deleted, version, created_at, created_by, sector_id, profession_id, total_hours)
SELECT
    false, 0, current_timestamp, null,
    (SELECT id FROM hospital_management.sectors WHERE name = 'Ultrassonografia' LIMIT 1),
    (SELECT id FROM profession_management.professions WHERE description = 'ENFERMEIRO(A)' LIMIT 1),
    1;

INSERT INTO hospital_management.sector_professions
    (deleted, version, created_at, created_by, sector_id, profession_id, total_hours)
SELECT
    false, 0, current_timestamp, null,
    (SELECT id FROM hospital_management.sectors WHERE name = 'Ultrassonografia' LIMIT 1),
    (SELECT id FROM profession_management.professions WHERE description = 'TÉCNICO(A) EM ENFERMAGEM' LIMIT 1),
    2;
