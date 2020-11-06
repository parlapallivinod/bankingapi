INSERT INTO role(rolename) VALUES('ROLE_BANKER');
INSERT INTO role(rolename) VALUES('ROLE_CUSTOMER');

-- testing customer registration
INSERT INTO user(username, password, balance, created_time) VALUES('user01', 'password01', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user01', 'ROLE_CUSTOMER');
-- user02 is used to register new user.

-- testing get customer
INSERT INTO user(username, password, balance, created_time) VALUES('user03', 'password03', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user03', 'ROLE_CUSTOMER');
-- user04 is used test get not existing customer

-- testing update password
INSERT INTO user(username, password, balance, created_time) VALUES('user05', 'password05', 0, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user05', 'ROLE_CUSTOMER');
-- user06 is used to test updating non existing user password

-- testing delete user
INSERT INTO user(username, password, balance, created_time) VALUES('user07', 'password07', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user07', 'ROLE_CUSTOMER');
-- user08 is used to test deleting non existing user
INSERT INTO user(username, password, balance, created_time) VALUES('user09', 'password09', 0, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user09', 'ROLE_CUSTOMER');

-- testing perform transaction
-- user10 is used to test invalid user
INSERT INTO user(username, password, balance, created_time) VALUES('user11', 'password11', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user11', 'ROLE_CUSTOMER');
INSERT INTO user(username, password, balance, created_time) VALUES('user12', 'password12', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user12', 'ROLE_CUSTOMER');

-- testing get transactions
-- user13 is used to test invalid user
INSERT INTO user(username, password, balance, created_time) VALUES('user14', 'password14', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user14', 'ROLE_CUSTOMER');
INSERT INTO user(username, password, balance, created_time) VALUES('user15', 'password15', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user15', 'ROLE_CUSTOMER');
INSERT INTO transaction(type, amount, from_user, to_user, created_time, status) VALUES('DEPOSIT', 100, null, 'user14', '2020-01-01 09:00:00', 'SUCCESS');
INSERT INTO transaction(type, amount, from_user, to_user, created_time, status) VALUES('WITHDRAW', 100, 'user14', null, '2020-01-01 09:00:00', 'SUCCESS');
INSERT INTO transaction(type, amount, from_user, to_user, created_time, status) VALUES('TRANSFER', 100, 'user14', 'user15', '2020-01-01 09:00:00', 'SUCCESS');


