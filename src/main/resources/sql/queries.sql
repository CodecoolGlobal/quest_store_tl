--user_types
CREATE TABLE user_types
(
  id serial,
  type text,
  PRIMARY KEY (id)
);
INSERT INTO user_types(type) VALUES('Codecooler');
INSERT INTO user_types(type) VALUES('Mentor');
INSERT INTO user_types(type) VALUES('Creepy Guy');

--item_types
CREATE TABLE item_type
(
  id serial,
  type text,
  grade text,
  PRIMARY KEY (id)
);
INSERT INTO item_types(type, grade) VALUES('Artifact', 'Normal');
INSERT INTO item_types(type, grade) VALUES('Artifact', 'Magic');
INSERT INTO item_types(type, grade) VALUES('Quest', 'Basic');
INSERT INTO item_types(type, grade) VALUES('Quest', 'Extra');

--rooms
CREATE TABLE rooms
(
  id serial,
  name text,
  PRIMARY KEY (id)
);
INSERT INTO rooms(name) VALUES('ProgBasic');
INSERT INTO rooms(name) VALUES('Java');
INSERT INTO rooms(name) VALUES('Web');
INSERT INTO rooms(name) VALUES('Advanced');

--levels
CREATE TABLE levels
(
  id serial,
  name text,
level_from int,
level_to int,
  PRIMARY KEY (id)
);
INSERT INTO levels(name, level_from, level_to) VALUES('junior', 0, 2000);
INSERT INTO levels(name, level_from, level_to) VALUES('medium', 2001, 4000);
INSERT INTO levels(name, level_from, level_to) VALUES('senior', 4001, 6000);

--statuses
CREATE TABLE statuses
(
  id serial,
  type text,
  PRIMARY KEY (id)
);
INSERT INTO statuses(type) VALUES('In progress');
INSERT INTO statuses(type) VALUES('Pending');
INSERT INTO statuses(type) VALUES('Realized');
INSERT INTO statuses(type) VALUES('Rejected');

--teams
CREATE TABLE teams
(
  id serial,
  name text,
  project_name text,
  PRIMARY KEY (id)
);
INSERT INTO teams(name, project_name) VALUES('foxes', 'Slack');
INSERT INTO teams(name, project_name) VALUES('bears', 'Quest store');
INSERT INTO teams(name, project_name) VALUES('sharks', 'Klondike');

--users
CREATE TABLE users
(
  id serial,
  name text,
  surname text,
  phone_number text,
  email text,
  password text,
  id_user_type int,
  id_team int,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user_type) REFERENCES user_types (id),
  FOREIGN KEY (id_team) REFERENCES teams (id)
);

--map_of_rooms
CREATE TABLE room_map
(
  id serial,
  id_user int,
  id_room int,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user) REFERENCES users (id),
  FOREIGN KEY (id_room) REFERENCES rooms (id)
);

--items
CREATE TABLE items
(
  id int,
  price int,
  title text,
  description text,
  item_type int,
  PRIMARY KEY (id),
  FOREIGN KEY (item_type) REFERENCES item_types (id)
);

--transactions
CREATE TABLE transactions
(
  id int,
  id_user int,
  id_team int,
  id_item int,
  id_status int,
  timestamp text,
  amount int,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user) REFERENCES users (id),
  FOREIGN KEY (id_team) REFERENCES teams (id),
  FOREIGN KEY (id_item) REFERENCES items (id),
  FOREIGN KEY (id_status) REFERENCES statuses (id)
);