INSERT INTO user (id, first_name, last_name, address, ssn, dob, username, password, role)
VALUES (1, 'Edmond', 'Basilan', '123 Main St', '123-45-6789', '1990-06-14', 'ebasilan@gmail.com', 'ebasilan', 'ADMIN'),
       (2, 'Tom', 'Lugo', '123 Main St', '123-45-6789', '1990-06-14', 'tlugo@yahoo.com', 'tlugo', 'USER'),
       (3, 'admin', 'admin', '123 Main St', '123-45-6789', '1990-06-14', 'admin', 'admin', 'ADMIN');

INSERT INTO account (id, account_type, account_number, User_id)
VALUES (1, 'Checking', '3604048489250263', 1),
       (2, 'Savings', '7370767860145505', 1);

INSERT INTO transactions (id, transaction_type, amount, date, Account_id)
VALUES (1, 'Deposit', 50.00, '2022-12-16', 1),
       (2, 'Withdraw', 25.25, '2022-12-17', 1),
       (3, 'Deposit', 2500.00, '2022-12-18', 2);