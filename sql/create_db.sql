create database accounting_system;
use accounting_system;

create table users
(
    id        int,
    full_name varchar(50),
    login     varchar(20),
    password  varchar(20),
    end_date  date,
    primary key (id)
);

create table contract_types
(
    id   int,
    name varchar(30) unique,
    primary key (id)
);

create table organisations
(
    id      int,
    name    varchar(150),
    address varchar(150),
    TIN     varchar(10),
    primary key (id)
);

create table organisation_contracts
(
    id                 int,
    name               varchar(100),
    contract_type_id   int,
    organisation_id    int,
    contract_id        int,
    amount             int,
    planned_start_date date,
    planned_end_date   date,
    actual_start_date  date,
    actual_end_date    date,
    primary key (id),
    foreign key (contract_type_id) references contract_types (id),
    foreign key (organisation_id) references organisations (id),
    foreign key (contract_id) references contracts (id)
);

create table contract_stages
(
    id                    int,
    name                  varchar(100),
    contract_id           int,
    amount                int,
    planned_start_date    date,
    planned_end_date      date,
    actual_start_date     date,
    actual_end_date       date,
    planned_material_cost int,
    actual_material_cost  int,
    planned_salary_cost   int,
    actual_salary_cost    int,
    primary key (id),
    foreign key (contract_id) references contracts (id)
);

create table contracts
(
    id                 int,
    name               varchar(100),
    amount             int,
    planned_start_date date,
    planned_end_date   date,
    actual_start_date  date,
    actual_end_date    date,
    primary key (id)
);