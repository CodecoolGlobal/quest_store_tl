ALTER TABLE transactions
    ALTER COLUMN timestamp TYPE timestamptz USING "timestamp"::timestamp with time zone;