INSERT INTO user (first_name, last_name, address, ssn, dob, username, password, role)
VALUES ('Edmond', 'Basilan', '123 Main St', '123-45-6789', '1990-06-14', 'ebasilan@gmail.com', 'ebasilan', 'ADMIN'),
       ('Tom', 'Lugo', '123 Main St', '123-45-6789', '2000-01-01', 'tlugo@yahoo.com', 'tlugo', 'USER'),
       ('admin', 'admin', '123 Main St', '123-45-6789', '1995-09-30', 'admin', 'admin', 'ADMIN');

INSERT INTO account (account_type, account_number, User_id)
VALUES ('Checking', '3604048489250263', 1),
       ('Savings', '7370767860145505', 1),
       ('Checking', '3604048489250263', 2),
       ('Savings', '7370767860145505', 3);

INSERT INTO transactions (transaction_type, amount, date, Account_id)
VALUES ('Deposit', 50.00, '2022-12-16', 1),
       ('Withdraw', 25.25, '2022-12-17', 1),
       ('Deposit', 2500.00, '2023-04-01', 2),
       ('Deposit', 3983.50, '2023-04-03', 3),
       ('Deposit', 483.98, '2023-04-05', 4);