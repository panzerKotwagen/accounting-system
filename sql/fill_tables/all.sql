insert into accounting_system.contract_types (name)
values ('Shipment'), ('Purchase'), ('Work');

insert into accounting_system.organisations (name, address, TIN)
values
    ('CAT Supplier', 'Chlumínská 12/721 904 18 Český Těšín Česká republika', '1234657891'),
    ('Company Buyer', 'Via Jelena 8 Appartamento 25 San Bibiana lido, 36610 Ragusa (BA)', '3214657891'),
    ('RTY Worker', 'Baranjska ulica 76, 58011 Mali Lošinj', '9874657891');

insert into accounting_system.contracts (name, contract_type_id, amount,
                                         planned_start_date, planned_end_date,
                                         actual_start_date, actual_end_date)
values ('contract №1', 1, 50000, '2024-1-13', '2024-02-02', '2024-1-13', '2024-02-02'),
       ('contract №2', 2, 125000, '2024-1-13', '2024-02-02', '2024-1-13', '2024-02-02'),
       ('contract №3', 3, 9565000, '2024-1-13', '2024-02-02', '2024-1-13', '2024-02-02');

insert into accounting_system.organisation_contracts(
    name, contract_type_id, organisation_id, contract_id, amount,
    planned_start_date, planned_end_date,
    actual_start_date, actual_end_date)

values ('Counterparty contract №1', 1, 1, 1, 30060, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №2', 2, 2, 1, 64060, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №3', 2, 2, 2, 90990, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №4', 3, 3, 3, 12360, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №5', 2, 2, 1, 45670, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25');

insert into accounting_system.contract_stages(
    name, contract_id, amount,
    planned_start_date, planned_end_date,
    actual_start_date, actual_end_date,
    planned_material_cost, actual_material_cost,
    planned_salary_cost, actual_salary_cost)
values ('Stage 1', 1, 3500, '2023-10-27', '2023-10-27',
        '2023-10-27', '2023-10-27', 2000, 2100, 1500, 1400),
       ('Stage 2', 1, 3500, '2023-10-27', '2023-10-27',
        '2023-10-27', '2023-10-27', 2000, 2100, 1500, 1400),
       ('Stage 3', 1, 3500, '2023-10-27', '2023-10-27',
        '2023-10-27', '2023-10-27', 2000, 2100, 1500, 1400),
       ('Stage 1', 2, 3500, '2023-10-27', '2023-10-27',
        '2023-10-27', '2023-10-27', 2000, 2100, 1500, 1400),
       ('Stage 2', 2, 3500, '2023-10-27', '2023-10-27',
        '2023-10-27', '2023-10-27', 2000, 2100, 1500, 1400),
       ('Stage 1', 3, 3500, '2023-10-27', '2023-10-27',
        '2023-10-27', '2023-10-27', 2000, 2100, 1500, 1400);
