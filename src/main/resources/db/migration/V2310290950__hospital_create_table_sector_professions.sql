create table if not exists "hospital_management"."sector_professions"
(
    "id"            uuid                        not null default gen_random_uuid() primary key,
    "deleted"       boolean                     not null default false,
    "version"       bigint                      not null,
    "created_at"    timestamp without time zone null,
    "created_by"    varchar(255)                null,
    "updated_at"    timestamp without time zone null,
    "updated_by"    varchar(255)                null,
    "sector_id"     uuid                        not null
        constraint fk_sector_professions_sector references "hospital_management"."sectors",
    "profession_id" uuid                        not null
        constraint fk_sector_professions_profession references "profession_management"."professions",
    "total_hours"   numeric(10, 2)              not null
)