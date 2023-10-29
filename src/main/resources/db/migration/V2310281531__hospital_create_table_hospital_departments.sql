create table if not exists "hospital_management"."hospital_departments"
(
    "hospital_id"   uuid not null
        constraint fk_hospital_departments_hospital references "hospital_management"."hospitals",
    "department_id" uuid not null
        constraint fk_hospital_departments_department references "hospital_management"."departments"
)