DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2016-05-11 10:00:31', 'breakfast', 500, 100000),
  ('2016-05-11 12:10:31', 'lunch', 800, 100000),
  ('2016-05-11 18:36:31', 'dinner', 650, 100000),
  ('2016-05-12 09:00:40', 'breakfast', 900, 100001),
  ('2016-05-12 17:56:40', 'dinner', 1200, 100001),
  ('2016-05-12 13:00:40', 'lunch', 1000, 100001),
  ('2016-05-12 10:00:31', 'breakfast', 500, 100000),
  ('2016-05-12 12:10:31', 'lunch', 1800, 100000),
  ('2016-05-12 18:36:31', 'dinner', 650, 100000);
