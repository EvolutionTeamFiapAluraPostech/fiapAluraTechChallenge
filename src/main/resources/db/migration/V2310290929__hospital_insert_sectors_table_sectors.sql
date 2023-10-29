INSERT INTO hospital_management.sectors
    (deleted, "version", created_at, created_by, updated_at, updated_by, active, name)
SELECT false, 0, current_timestamp, null, null,
       NULL, true, 'Raio-X'
WHERE (SELECT count(*)
         FROM hospital_management.sectors s2
        WHERE name = 'Raio-X') = 0;

INSERT INTO hospital_management.sectors
(deleted, "version", created_at, created_by, updated_at, updated_by, active, name)
SELECT false, 0, current_timestamp, null, null,
       NULL, true, 'Ultrassonografia'
WHERE (SELECT count(*)
       FROM hospital_management.sectors s2
       WHERE name = 'Ultrassonografia') = 0;
