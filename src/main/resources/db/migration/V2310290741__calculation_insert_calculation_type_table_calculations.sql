INSERT INTO calculation_management.calculations (deleted, "version", created_at, created_by, updated_at,
                                             updated_by, active, description)
SELECT false, 0, current_timestamp, null, null,
       NULL, true, 'Cálculo para Unidade Assistencial, Apoio, Diagnóstico e Terapêutica'
WHERE (SELECT count(*)
         FROM calculation_management.calculations p2
        WHERE description = 'Cálculo para Unidade Assistencial, Apoio, Diagnóstico e Terapêutica') = 0;
