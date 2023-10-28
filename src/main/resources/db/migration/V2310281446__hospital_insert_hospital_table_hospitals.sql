INSERT INTO hospital_management.hospitals (deleted, "version", created_at, updated_at, active,
                                           name, created_by, updated_by)
SELECT false, 0, current_timestamp, null, true, 'International Hospital', null, NULL
WHERE (SELECT count(*) FROM hospital_management.hospitals h2) = 0;