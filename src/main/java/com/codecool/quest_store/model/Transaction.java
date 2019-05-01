package com.codecool.quest_store.model;

import java.time.LocalDate;

public class Transaction {

    private final int ID;
    private final int FUNDING_ID;
    private final int USER_ID;
    private final int TEAM_ID;
    private final int ITEM_ID;
    private final int STATUS_ID;
    private final LocalDate TIMESTAMP;
    private final int PAID_AMOUNT;

    private Transaction(Builder builder) {
        this.ID = builder.ID;
        this.FUNDING_ID = builder.FUNDING_ID;
        this.USER_ID = builder.USER_ID;
        this.TEAM_ID = builder.TEAM_ID;
        this.ITEM_ID = builder.ITEM_ID;
        this.STATUS_ID = builder.STATUS_ID;
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

    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public int getSTATUS_ID() {
        return STATUS_ID;
    }

    public LocalDate getTIMESTAMP() {
        return TIMESTAMP;
    }

    public int getPAID_AMOUNT() {
        return PAID_AMOUNT;
    }

    public static class Builder {
        private int ID;
        private int FUNDING_ID;
        private int USER_ID;
        private int TEAM_ID;
        private int ITEM_ID;
        private int STATUS_ID;
        private LocalDate TIMESTAMP;
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

        public Builder withTEAM_ID(int TEAM_ID) {
            this.TEAM_ID = TEAM_ID;
            return this;
        }

        public Builder withITEM_ID(int ITEM_ID) {
            this.ITEM_ID = ITEM_ID;
            return this;
        }

        public Builder withSTAUS_ID(int STATUS_ID) {
            this.STATUS_ID = STATUS_ID;
            return this;
        }

        public Builder withTIMESTAMP(LocalDate TIMESTAMP) {
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