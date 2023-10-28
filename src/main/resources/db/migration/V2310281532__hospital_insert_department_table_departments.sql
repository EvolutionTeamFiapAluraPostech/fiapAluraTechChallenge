INSERT INTO hospital_management.departments (deleted, "version", created_at, created_by, updated_at,
                                             updated_by, active, name)
SELECT false, 0, current_timestamp, null, null,
       NULL, true, 'Unidade Assistencial, Apoio, Diagnóstico e Terapêutica'
WHERE (SELECT count(*) FROM hospital_management.departments d2) = 0;