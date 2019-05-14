CREATE TABLE session
(
  id serial,
  session int,
  user_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);