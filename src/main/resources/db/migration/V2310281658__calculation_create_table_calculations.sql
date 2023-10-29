create schema if not exists "calculation_management";

create table if not exists "calculation_management"."calculations"
(
    "id"          uuid                        not null default gen_random_uuid() primary key,
    "deleted"     boolean                     not null default false,
    "version"     bigint                      not null,
    "created_at"  timestamp without time zone null,
    "created_by"  varchar(255)                null,
    "updated_at"  timestamp without time zone null,
    "updated_by"  varchar(255)                null,
    "active"      boolean                     not null default true,
    "description" varchar(255)                null
);
