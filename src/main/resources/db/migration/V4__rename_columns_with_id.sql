ALTER TABLE map_of_rooms
    RENAME COLUMN id_user TO user_id;

ALTER TABLE map_of_rooms
    RENAME COLUMN id_room TO room_id;

ALTER TABLE transactions
    RENAME COLUMN id_funding TO funding_id;

ALTER TABLE transactions
    RENAME COLUMN id_user TO user_id;

ALTER TABLE users
    RENAME COLUMN id_user_type TO user_type_id;

ALTER TABLE users
    RENAME COLUMN id_room TO room_id;

ALTER TABLE users
    RENAME COLUMN id_team TO team_id;