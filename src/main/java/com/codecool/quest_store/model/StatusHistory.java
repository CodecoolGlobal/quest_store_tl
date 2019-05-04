package com.codecool.quest_store.model;

import java.time.LocalDate;

public class StatusHistory {

    private final int FUNDING_ID;
    private final LocalDate TIMESTAMP;
    private final int STATUS_ID;

    private StatusHistory(Builder builder) {
        this.FUNDING_ID = builder.FUNDING_ID;
        this.TIMESTAMP = builder.TIMESTAMP;
        this.STATUS_ID = builder.STATUS_ID;
    }

    public int getFUNDING_ID() {
        return FUNDING_ID;
    }

    public LocalDate getTIMESTAMP() {
        return TIMESTAMP;
    }

    public int getSTATUS_ID() {
        return STATUS_ID;
    }

    public static class Builder {
        private int FUNDING_ID;
        private LocalDate TIMESTAMP;
        private int STATUS_ID;

        public Builder withFUNDING_ID(int FUNDING_ID) {
            this.FUNDING_ID = FUNDING_ID;
            return this;
        }

        public Builder withTIMESTAMP(LocalDate TIMESTAMP) {
            this.TIMESTAMP = TIMESTAMP;
            return this;
        }

        public Builder withSTATUS_ID(int STATUS_ID) {
            this.STATUS_ID = STATUS_ID;
            return this;
        }

        public StatusHistory build() {
            return new StatusHistory(this);
        }
    }
}