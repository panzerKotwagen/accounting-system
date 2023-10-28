insert into accounting_system.organisation_contracts(
    name, contract_type_id, organisation_id, contract_id, amount,
    planned_start_date, planned_end_date,
    actual_start_date, actual_end_date)

values ('Counterparty contract №1', 1, 1, 1, 30060, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №2', 2, 2, 1, 64060, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №3', 2, 2, 2, 90990, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №4', 3, 3, 3, 12360, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25'),
       ('Counterparty contract №5', 2, 2, 1, 45670, '2023-05-25', '2023-05-25', '2023-05-25', '2023-05-25');