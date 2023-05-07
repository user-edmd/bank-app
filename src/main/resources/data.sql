INSERT INTO user (first_name, last_name, address, ssn, dob, username, password, role)
VALUES ('Edmond', 'Basilan', '123 A St San Diego, CA', '123-45-6789', '1990-06-14', 'ebasilan@gmail.com', 'ebasilan', 'ADMIN'),
       ('Tom', 'Lugo', '456 B St San Diego, CA', '325-46-8779', '2000-01-01', 'tlugo@yahoo.com', 'tlugo', 'USER'),
       ('Yvette', 'Cao', '710 Oslo Way San Diego, CA', '928-82-6833', '1990-12-12', 'ycao@grossmont.com', 'ycao', 'USER'),
       ('admin', 'admin', '789 C St San Diego, CA', '643-02-1054 ', '1995-09-30', 'admin', 'admin', 'ADMIN');

INSERT INTO account (account_type, account_number, User_id)
VALUES ('Checking', '3604048489250263', 1),
       ('Savings', '7370767860145505', 1),
       ('Checking', '4695382874482366', 2),
       ('Savings', '1305385097666747', 3);

INSERT INTO transactions (transaction_type, amount, date, Account_id)
VALUES ('Deposit', 5000.00, '2022-12-16', 1),
       ('Withdraw', 25.25, '2022-12-17', 1),
       ('Deposit', 2500.00, '2023-04-01', 2),
       ('Deposit', 3983.50, '2023-04-03', 3),
       ('Deposit', 483.98, '2023-04-05', 4),
       ('Deposit', 650.53, '2023-04-05', 1),
       ('Deposit', 275.91, '2023-04-05', 1),
       ('Deposit', 447.04, '2023-04-05', 1),
       ('Deposit', 408.04, '2023-04-05', 1),
       ('Deposit', 909.58, '2023-04-05', 1),
       ('Deposit', 368.78, '2023-04-05', 1),
       ('Deposit', 638.51, '2023-04-05', 1),
       ('Deposit', 617.74, '2023-04-05', 1),
       ('Deposit', 254.59, '2023-04-05', 1),
       ('Deposit', 794.31, '2023-04-05', 1),
       ('Deposit', 592.67, '2023-04-05', 1),
       ('Deposit', 826.27, '2023-04-05', 1),
       ('Deposit', 681.33, '2023-04-05', 1),
       ('Deposit', 405.98, '2023-04-05', 1),
       ('Deposit', 347.53, '2023-04-05', 1),
       ('Deposit', 922.41, '2023-04-05', 1),
       ('Deposit', 841.40, '2023-04-05', 1),
       ('Deposit', 117.24, '2023-04-05', 1),
       ('Deposit', 136.16, '2023-04-05', 1),
       ('Deposit', 507.07, '2023-04-05', 1);

INSERT INTO users (username, password, enabled)
  values ('ebasilan@gmail.com', '{noop}ebasilan', 1),
         ('tlugo@yahoo.com', '{noop}tlugo', 1);

INSERT INTO authorities (username, authority)
  values ('ebasilan@gmail.com', 'ROLE_ADMIN'),
         ('tlugo@yahoo.com', 'ROLE_USER');