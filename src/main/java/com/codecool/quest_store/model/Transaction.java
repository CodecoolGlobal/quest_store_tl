package com.codecool.quest_store.model;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final int ID;
    private final int FUNDING_ID;
    private final int USER_ID;
    private final OffsetDateTime TIMESTAMP;
    private final int PAID_AMOUNT;

    private Transaction(Builder builder) {
        this.ID = builder.ID;
        this.FUNDING_ID = builder.FUNDING_ID;
        this.USER_ID = builder.USER_ID;
        this.TIMESTAMP = builder.TIMESTAMP;
        this.PAID_AMOUNT = builder.PAID_AMOUNT;
    }

    public int getID() {
        return ID;
    }

    public int getFUNDING_ID() {
        return FUNDING_ID;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public OffsetDateTime getTIMESTAMP() {
        return TIMESTAMP;
    }

    public String getFormattedDate() {
        String format = "dd/MM/yy E hh:mm a";
        return TIMESTAMP.format(DateTimeFormatter.ofPattern(format));
    }

    public int getPAID_AMOUNT() {
        return PAID_AMOUNT;
    }

    public static class Builder {
        private int ID;
        private int FUNDING_ID;
        private int USER_ID;
        private OffsetDateTime TIMESTAMP;
        private int PAID_AMOUNT;

        public Builder withID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder withFUNDING_ID(int FUNDING_ID) {
            this.FUNDING_ID = FUNDING_ID;
            return this;
        }

        public Builder withUSER_ID(int USER_ID) {
            this.USER_ID = USER_ID;
            return this;
        }

        public Builder withTIMESTAMP(OffsetDateTime TIMESTAMP) {
            this.TIMESTAMP = TIMESTAMP;
            return this;
        }

        public Builder withPAID_AMOUNT(int PAID_AMOUNT) {
            this.PAID_AMOUNT = PAID_AMOUNT;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}