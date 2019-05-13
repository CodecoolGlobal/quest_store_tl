ALTER TABLE levels
    DROP COLUMN level_to;

ALTER TABLE transactions
    DROP COLUMN id_team,
    DROP COLUMN id_status,
    DROP COLUMN id_item;