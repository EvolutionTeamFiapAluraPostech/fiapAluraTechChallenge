INSERT INTO profession_management.professions (deleted, "version", created_at, created_by, updated_at,
                                             updated_by, active, description)
SELECT false, 0, current_timestamp, null, null,
       NULL, true, 'ENFERMEIRO(A)'
WHERE (SELECT count(*) FROM profession_management.professions p2 WHERE description = 'ENFERMEIRO(A)') = 0;

INSERT INTO profession_management.professions (deleted, "version", created_at, created_by, updated_at,
                                               updated_by, active, description)
SELECT false, 0, current_timestamp, null, null,
       NULL, true, 'TÉCNICO(A) EM ENFERMAGEM'
WHERE (SELECT count(*) FROM profession_management.professions p2 WHERE description = 'TÉCNICO(A) EM ENFERMAGEM') = 0;