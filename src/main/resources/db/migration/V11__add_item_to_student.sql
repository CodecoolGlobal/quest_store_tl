INSERT INTO fundings(item_id, team_id)
VALUES(14, null);--

INSERT INTO fundings(item_id, team_id)
VALUES(1, null);

INSERT INTO transactions(funding_id, user_id, timestamp, paid_amount)
VALUES(1, 1, null, 50);

INSERT INTO transactions(funding_id, user_id, timestamp, paid_amount)
VALUES(2, 1, null, 100);

INSERT INTO status_history(funding_id, status_id, timestamp)
VALUES(1, 2, null);

INSERT INTO status_history(funding_id, status_id, timestamp)
VALUES(2, 2, null);

SELECT * FROM items
INNER JOIN fundings ON items.id = fundings.item_id
INNER JOIN transactions ON transactions.funding_id = fundings.id
INNER JOIN status_history ON status_history.funding_id = fundings.id
INNER JOIN statuses ON statuses.id = status_history.status_id
WHERE transactions.user_id = 1
AND statuses.type = 'Pending';