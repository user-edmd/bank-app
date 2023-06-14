INSERT INTO user (first_name, last_name, address, ssn, dob, username, password, role)
VALUES ('Edmond', 'Basilan', '123 A St San Diego, CA', '123-45-6789', '1990-06-14', 'ebasilan@gmail.com', 'ebasilan', 'ADMIN'),
       ('Tom', 'Lugo', '456 B St San Diego, CA', '325-46-8779', '2000-01-01', 'tlugo@yahoo.com', 'tlugo', 'USER'),
       ('Yvette', 'Cao', '710 Oslo Way San Diego, CA', '928-82-6833', '1990-12-12', 'ycao@grossmont.com', 'ycao', 'USER'),
       ('admin', 'admin', '789 C St San Diego, CA', '643-02-1054 ', '1995-09-30', 'admin', 'admin', 'ADMIN');

INSERT INTO account (account_type, account_number, User_id)
VALUES ('Checking', '0000000000000001', 1),
       ('Savings', '0000000000000002', 1),
       ('Checking', '0000000000000003', 2),
       ('Savings', '0000000000000004', 3);

INSERT INTO transactions (transaction_type, amount, date, Account_id)
VALUES ('Deposit', 5000.00, '2000-01-01', 1),
        ('Deposit', 5000.00, '2000-01-01', 2),
        ('Deposit', 5000.00, '2000-01-01', 3),
        ('Deposit', 5000.00, '2000-01-01', 4),
       ('Deposit', 97.68, '2018-07-22', 3),
       ('Deposit', 366.36, '2019-09-05', 2),
       ('Deposit', 424.58, '2013-05-21', 2),
       ('Deposit', 337.89, '2016-06-25', 3),
       ('Deposit', 133.04, '2004-05-11', 1),
       ('Deposit', 462.54, '2008-06-15', 2),
       ('Deposit', 372.20, '2000-01-09', 4),
       ('Deposit', 194.79, '2015-06-27', 3),
       ('Deposit', 220.32, '2019-08-03', 2),
       ('Deposit', 232.67, '2005-08-09', 4),
       ('Deposit', 217.57, '2018-10-15', 3),
       ('Deposit', 147.49, '2008-07-19', 1),
       ('Deposit', 274.26, '2012-07-01', 1),
       ('Deposit', 39.29, '2010-01-19', 1),
       ('Deposit', 218.03, '2004-02-02', 3),
       ('Deposit', 298.80, '2021-10-25', 1),
       ('Deposit', 180.14, '2022-11-17', 1),
       ('Deposit', 290.38, '2020-06-22', 4),
       ('Deposit', 475.89, '2023-08-24', 4),
       ('Deposit', 361.27, '2000-06-07', 1),
       ('Deposit', 84.35, '2021-09-04', 2),
       ('Deposit', 88.36, '2011-08-13', 2),
       ('Deposit', 445.71, '2022-09-07', 2),
       ('Deposit', 387.49, '2000-02-05', 2),
       ('Deposit', 106.26, '2012-09-18', 2),
       ('Deposit', 290.95, '2002-12-07', 3),
       ('Deposit', 312.17, '2014-05-20', 1),
       ('Deposit', 84.14, '2004-04-27', 1),
       ('Deposit', 96.33, '2022-11-21', 1),
       ('Deposit', 81.55, '2005-06-10', 1),
       ('Deposit', 161.78, '2020-03-22', 1),
       ('Deposit', 118.45, '2015-02-11', 3),
       ('Deposit', 221.25, '2001-03-18', 3),
       ('Deposit', 148.02, '2018-10-19', 3),
       ('Deposit', 201.53, '2007-10-27', 2),
       ('Deposit', 371.45, '2006-07-04', 1),
       ('Deposit', 142.36, '2013-11-06', 1),
       ('Deposit', 462.05, '2000-05-14', 4),
       ('Deposit', 371.83, '2001-05-26', 4),
       ('Deposit', 396.60, '2005-02-15', 1),
       ('Deposit', 129.96, '2000-11-04', 2),
       ('Deposit', 28.08, '2018-03-05', 2),
       ('Deposit', 161.51, '2006-02-04', 1),
       ('Deposit', 143.75, '2023-09-04', 2),
       ('Deposit', 62.60, '2005-12-25', 2),
       ('Deposit', 250.12, '2008-12-14', 1),
       ('Deposit', 437.77, '2019-04-28', 1),
       ('Deposit', 278.17, '2010-08-15', 2),
       ('Deposit', 317.06, '2013-12-03', 3),
       ('Deposit', 386.77, '2020-05-18', 4),
       ('Deposit', 324.88, '2009-09-18', 4),
       ('Deposit', 381.52, '2015-12-27', 4),
       ('Deposit', 211.91, '2011-09-27', 2),
       ('Deposit', 459.89, '2015-04-15', 3),
       ('Deposit', 351.17, '2021-04-03', 3),
       ('Deposit', 256.78, '2005-08-12', 1),
       ('Deposit', 204.94, '2010-03-27', 1),
       ('Deposit', 360.14, '2011-04-02', 3),
       ('Deposit', 125.25, '2019-01-11', 3),
       ('Deposit', 462.27, '2023-05-21', 4),
       ('Deposit', 342.40, '2008-06-08', 2),
       ('Deposit', 283.07, '2000-07-16', 4),
       ('Deposit', 449.09, '2002-01-14', 4),
       ('Deposit', 20.35, '2020-11-28', 2),
       ('Deposit', 476.03, '2016-03-06', 3),
       ('Deposit', 392.96, '2001-11-12', 4),
       ('Deposit', 277.47, '2007-05-09', 3),
       ('Deposit', 25.17, '2002-09-25', 3),
       ('Deposit', 462.21, '2012-04-14', 1),
       ('Deposit', 67.85, '2023-11-26', 4),
       ('Deposit', 120.39, '2002-04-20', 2),
       ('Deposit', 450.27, '2015-12-22', 1),
       ('Deposit', 336.20, '2022-09-03', 4),
       ('Deposit', 408.20, '2015-10-16', 2),
       ('Deposit', 97.82, '2021-10-24', 2),
       ('Deposit', 270.68, '2020-09-19', 1),
       ('Deposit', 43.29, '2021-09-17', 3),
       ('Deposit', 253.37, '2015-09-19', 3),
       ('Deposit', 113.92, '2002-12-27', 2),
       ('Deposit', 156.58, '2003-09-04', 2),
       ('Deposit', 118.25, '2021-05-17', 2),
       ('Deposit', 134.93, '2004-09-02', 3),
       ('Deposit', 470.96, '2008-09-11', 3),
       ('Deposit', 195.23, '2019-11-10', 1),
       ('Deposit', 403.55, '2009-11-08', 1),
       ('Deposit', 243.21, '2015-03-02', 3),
       ('Deposit', 441.80, '2022-03-02', 3),
       ('Deposit', 485.30, '2019-07-15', 3),
       ('Deposit', 147.23, '2002-05-06', 3),
       ('Deposit', 493.75, '2015-06-08', 2),
       ('Deposit', 353.53, '2007-02-01', 2),
       ('Deposit', 43.57, '2002-01-02', 3),
       ('Deposit', 21.24, '2010-03-16', 4),
       ('Deposit', 343.41, '2012-10-28', 2),
       ('Deposit', 203.86, '2011-08-16', 3),
       ('Deposit', 226.93, '2010-07-05', 3),
       ('Deposit', 141.50, '2014-11-04', 1),
       ('Deposit', 161.92, '2016-09-16', 1),
       ('Deposit', 306.57, '2010-05-25', 3),
       ('Deposit', 373.67, '2008-01-27', 1),
       ('Withdraw', 82.54, '2008-01-19', 2),
       ('Withdraw', 76.52, '2009-06-22', 2),
       ('Withdraw', 290.11, '2010-09-25', 2),
       ('Withdraw', 434.49, '2016-10-10', 4),
       ('Withdraw', 336.19, '2018-12-10', 3),
       ('Withdraw', 22.16, '2008-11-12', 1),
       ('Withdraw', 266.22, '2013-11-17', 4),
       ('Withdraw', 281.58, '2015-02-08', 3),
       ('Withdraw', 378.47, '2006-10-24', 4),
       ('Withdraw', 332.05, '2023-07-28', 1),
       ('Withdraw', 312.91, '2003-05-03', 4),
       ('Withdraw', 134.68, '2006-10-04', 3),
       ('Withdraw', 26.03, '2006-04-07', 3),
       ('Withdraw', 346.66, '2006-11-11', 1),
       ('Withdraw', 37.44, '2007-03-16', 1),
       ('Withdraw', 405.67, '2008-01-23', 2),
       ('Withdraw', 135.60, '2019-10-06', 1),
       ('Withdraw', 355.37, '2021-04-09', 3),
       ('Withdraw', 409.29, '2007-07-07', 1),
       ('Withdraw', 446.71, '2004-04-25', 4),
       ('Withdraw', 397.53, '2007-03-20', 3),
       ('Withdraw', 194.51, '2017-08-24', 3),
       ('Withdraw', 16.14, '2005-01-28', 3),
       ('Withdraw', 161.78, '2011-02-17', 2),
       ('Withdraw', 451.30, '2019-10-09', 2),
       ('Withdraw', 363.29, '2020-10-06', 2),
       ('Withdraw', 192.55, '2012-04-27', 4),
       ('Withdraw', 444.41, '2022-09-21', 4),
       ('Withdraw', 123.20, '2011-06-06', 3),
       ('Withdraw', 170.10, '2007-05-19', 1),
       ('Withdraw', 490.89, '2009-03-16', 2),
       ('Withdraw', 434.10, '2004-10-10', 1),
       ('Withdraw', 214.18, '2008-04-22', 4),
       ('Withdraw', 158.50, '2000-01-05', 4),
       ('Withdraw', 50.55, '2001-09-16', 3),
       ('Withdraw', 215.25, '2011-04-04', 4),
       ('Withdraw', 312.61, '2017-01-19', 4),
       ('Withdraw', 210.29, '2001-11-13', 3),
       ('Withdraw', 71.45, '2010-03-13', 2),
       ('Withdraw', 360.09, '2005-09-24', 2),
       ('Withdraw', 52.92, '2002-05-13', 4),
       ('Withdraw', 62.93, '2007-11-03', 4),
       ('Withdraw', 332.11, '2005-03-16', 1),
       ('Withdraw', 11.41, '2023-01-18', 3),
       ('Withdraw', 295.75, '2005-01-08', 1),
       ('Withdraw', 139.75, '2003-07-21', 4),
       ('Withdraw', 470.98, '2009-02-13', 1),
       ('Withdraw', 181.79, '2007-04-25', 4),
       ('Withdraw', 269.58, '2003-02-09', 4),
       ('Withdraw', 404.61, '2009-07-18', 2);

INSERT INTO users (username, password, enabled)
  values ('ebasilan@gmail.com', '{noop}ebasilan', 1),
         ('tlugo@yahoo.com', '{noop}tlugo', 1);

INSERT INTO authorities (username, authority)
  values ('ebasilan@gmail.com', 'ROLE_ADMIN'),
         ('tlugo@yahoo.com', 'ROLE_USER');