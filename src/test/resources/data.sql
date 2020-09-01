INSERT INTO role(rolename) VALUES('ROLE_BANKER');
INSERT INTO role(rolename) VALUES('ROLE_CUSTOMER');

INSERT INTO user(username, password, balance, created_time) VALUES('user01', 'password01', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user01', 'ROLE_CUSTOMER');
INSERT INTO user(username, password, balance, created_time) VALUES('user02', 'password02', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user02', 'ROLE_CUSTOMER');
INSERT INTO user(username, password, balance, created_time) VALUES('user03', 'password03', 1000, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user03', 'ROLE_CUSTOMER');

INSERT INTO user(username, password, balance, created_time) VALUES('user05', 'password05', 0, '2020-01-01 09:00:00');
INSERT INTO user_role(username, rolename) VALUES('user05', 'ROLE_CUSTOMER');



