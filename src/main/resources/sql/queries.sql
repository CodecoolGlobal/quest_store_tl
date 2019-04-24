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
CREATE TABLE item_types
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
  id_room int,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user_type) REFERENCES user_types (id),
  FOREIGN KEY (id_team) REFERENCES teams (id),
  FOREIGN KEY (id_room) REFERENCES rooms (id)
);

--map_of_rooms
CREATE TABLE map_of_rooms
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
  id serial,
  price int,
  title text,
  description text,
  item_type int,
  PRIMARY KEY (id),
  FOREIGN KEY (item_type) REFERENCES item_types (id)
);

INSERT INTO items(price, title, description, item_type)
VALUES(100, 'Exploring a dungeon', 'Finishing a Teamwork week', 3);
INSERT INTO items(price, title, description, item_type)
VALUES(100, 'Solving the magic puzzle', 'Finishing an SI assignment', 3);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Slaying a dragon', 'Passing a Checkpoint', 3);
INSERT INTO items(price, title, description, item_type)
VALUES(50, 'Spot trap', 'Spot a major mistake in the assignment', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(100, 'Taming a pet', 'Doing a demo about a pet project', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(100, 'Recruiting some n00bs', 'Taking part in the student screening process', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(400, 'Forging weapons', 'Organizing a workshop for other students', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(300, 'Master the mornings', 'Attend 1 months without being late', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Fast as an unicorn', 'Deliver 4 consecutive SI week assignments on time', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Achiever', 'Set up a SMART goal accepted by a mentor, then achieve it', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Fortune', 'Students choose the best project of the week. Selected team scores', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Creating an enchanted scroll', 'Creating extra material for the current TW/SI topic (should be revised by mentors)', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Enter the arena', 'Do a presentation on a meet-up', 4);
INSERT INTO items(price, title, description, item_type)
VALUES(50, 'Combat training', 'Private mentoring', 1);
INSERT INTO items(price, title, description, item_type)
VALUES(300, 'Sanctuary', 'You can spend a day in home office', 1);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Time Travel', 'extend SI week assignment deadline by one day', 1);
INSERT INTO items(price, title, description, item_type)
VALUES(1000, 'Circle of Sorcery', '60 min workshop by a mentor(s) of the chosen topic', 2);
INSERT INTO items(price, title, description, item_type)
VALUES(1000, 'Summon Code Elemental', 'mentor joins a students team for a one hour', 2);
INSERT INTO items(price, title, description, item_type)
VALUES(500, 'Tome of knowledge', 'Extra material for the current topic', 2);
INSERT INTO items(price, title, description, item_type)
VALUES(5000, 'Transform mentors', 'All mentors should dress up as pirates (or just funny) for the day', 2);
INSERT INTO items(price, title, description, item_type)
VALUES(30000, 'Teleport ', 'The whole course goes to an off-school program instead for a day', 2);

--transactions
CREATE TABLE transactions
(
  id serial,
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