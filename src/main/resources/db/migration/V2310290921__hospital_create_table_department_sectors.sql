create table if not exists "hospital_management"."department_sectors"
(
    "department_id" uuid not null
        constraint fk_department_sectors_department references "hospital_management"."departments",
    "sector_id"     uuid not null
        constraint fk_department_sectors_sector references "hospital_management"."sectors"
)