DELETE FROM user_roles;
DELETE FROM tools;
DELETE FROM users;
DELETE FROM locations;
DELETE FROM types;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE start_seq RESTART WITH 1;

INSERT INTO users (name, password)
VALUES ('user', '{noop}password'),
       ('admin', '{noop}admin'),
       ('editor', '{noop}editor');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001),
       ('EDITOR', 100001),
       ('USER', 100002),
       ('EDITOR', 100002);

INSERT INTO locations (name)
VALUES ('БИХ 101'),
       ('БИХ 102'),
       ('БИХ 103'),
       ('БИХ 104'),
       ('БИХ 104(18)'),
       ('БИХ 104(24)'),
       ('БИХ 106'),
       ('БИХ 107'),
       ('БИХ 108'),
       ('БИХ 110'),
       ('БИХ 111'),
       ('БИХ 114'),
       ('БИХ 116'),
       ('БИХ 119'),
       ('БИХ 126'),
       ('БИХ 220'),
       ('БИХ 221'),
       ('БИХ 222'),
       ('БИХ 223'),
       ('БИХ ОГС'),
       ('БИХ ОТК'),
       ('БИХ СГП'),
       ('БИХ ТЦ'),
       ('БИХ УЗП'),
       ('БИХ ЦЗЛ'),
       ('БИХ ЭЦ'),
       ('ЦИС');

INSERT INTO types (name, parent_id, level, final_type)
VALUES ('Режущий инструмент', 0, 0, false),
       ('Токарный', 100003, 1, false),
       ('Вращающийся', 100003, 1, false),
       ('Цельный', 100005, 2, false),
       ('Метчики', 100006, 3, true),
       ('Сверла спиральные', 100006, 3, true),
       ('Фрезы монолитные', 100006, 3, true),
       ('С механическим креплением', 100005, 2, false),
       ('Корпуса фрез', 100010, 3, true),
       ('Корпуса сверл', 100010, 3, true),
       ('Режущая часть', 0, 0, false),
       ('Токарная', 100013, 1, false),
       ('Пластинки для проходных резцов', 100014, 2, true),
       ('Пластинки для расточных резцов', 100014, 2, true),
       ('Вращающаяся', 100013, 1, false);

INSERT INTO tools (registration_date, description, tools_count, manufacturer, location, deficiency, type, user_id)
VALUES ('2020-11-15', 'CNMG 190616-PM 4035', 5, 'Sandvik', 14, 5, 100015, 100002),
       ('2020-11-18', 'CNMM 120408-PR 4415', 50, 'Sandvik', 4, 10, 100015, 100002),
       ('2020-12-28', 'R390-040Q16LW-11L', 1, 'Sandvik', 3, 1, 100011, 100002),
       ('2020-12-30', 'EC-H4M 06-12C06CF-E57', 1, 'Iscar', 16, 2, 100009, 100002),
       ('2021-01-5', '137860 1-8', 25, 'Hoffmann', 3, 5, 100007, 100002),
       ('2021-01-7', '215126 50/3', 3, 'Hoffmann', 4, 1, 100011, 100002),
       ('2021-01-18', 'A6181AML-2', 10, 'Walter', 4, 5, 100008, 100002),
       ('2021-01-18', 'SF25DRA180M12', 2, 'Kyocera', 4, 1, 100008, 100001),
       ('2021-01-21', 'MEV 1250W100064T', 3, 'Kyocera', 3, 1, 100011, 100001);
