create table if not exists "calculation_management"."staffing_board"
(
    "id"                      uuid                        not null default gen_random_uuid() primary key,
    "deleted"                 boolean                     not null default false,
    "version"                 bigint                      not null,
    "created_at"              timestamp without time zone null,
    "created_by"              varchar(255)                null,
    "updated_at"              timestamp without time zone null,
    "updated_by"              varchar(255)                null,
    "state"                   boolean                     not null default true,
    "hospital_id"             uuid                        not null
        constraint fk_hospital_staffing_board references "hospital_management"."hospitals",
    "department_id"           uuid                        not null
        constraint fk_department_staffing_board references "hospital_management"."departments",
    "profession_id"           uuid                        not null
        constraint fk_profession_staffing_board references "profession_management"."professions",
    "calculation_id"          uuid                        not null
        constraint fk_calculation_staffing_board references "calculation_management"."calculations",
    "total_hours"             numeric(10, 2)              null,
    "marinho_constant"        numeric(10, 4)              null,
    "number_of_collaborators" smallint                    null,
    "calculation_memory"      varchar                     null
);
