INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO app_user (id, username, password) VALUES
(1, 'admin', '$2a$10$LJ9lROdv4F1HtoOs14I9UeYbfxCpQQkTy2mFpMisW7guwuaIkv4gy');

INSERT INTO app_user (id, username, password) VALUES
(2, 'ravi', '$2a$10$GdVeU3.ykpktmU4xE.wC3uifc.oGt8sKQdO75bFfWBzrdyUavVmiO');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);