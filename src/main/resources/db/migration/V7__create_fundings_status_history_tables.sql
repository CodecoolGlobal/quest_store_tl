CREATE TABLE fundings (
    id serial,
    item_id int,
    team_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES items (id),
    FOREIGN KEY (team_id) REFERENCES teams (id)
);

CREATE TABLE status_history (
    id serial,
    funding_id int,
    status_id int,
    timestamp timestamptz,
    PRIMARY KEY (id),
    FOREIGN KEY (funding_id) REFERENCES fundings (id),
    FOREIGN KEY (status_id) REFERENCES statuses (id)
);