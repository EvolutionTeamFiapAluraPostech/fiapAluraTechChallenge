create table if not exists "hospital_management"."departments"
(
    "id"           uuid                        not null default gen_random_uuid() primary key,
    "deleted"      boolean                     not null default false,
    "version"      bigint                      not null,
    "created_at"   timestamp without time zone null,
    "created_by"   varchar(255)                null,
    "updated_at"   timestamp without time zone null,
    "updated_by"   varchar(255)                null,
    "active"       boolean                     not null default true,
    "name"         varchar(255)                not null,
    "calculate_id" smallint                    null
);
