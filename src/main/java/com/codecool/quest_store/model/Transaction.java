package com.codecool.quest_store.model;

import java.time.LocalDate;

public class Transaction {

    private int id;
    private int idFunding;
    private int userId;
    private int teamId;
    private int itemId;
    private int statusId;
    private LocalDate timestamp;
    private int paidAmount;

    private Transaction(Builder builder) {
        this.id = builder.id;
        this.idFunding = builder.idFunding;
        this.userId = builder.userId;
        this.teamId = builder.teamId;
        this.itemId = builder.itemId;
        this.statusId = builder.statusId;
        this.timestamp = builder.timestamp;
        this.paidAmount = builder.paidAmount;
    }

    public int getId() {
        return id;
    }

    public int getIdFunding() {
        return idFunding;
    }

    public int getUserId() {
        return userId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getStatusId() {
        return statusId;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public static class Builder {
        private int id;
        private int idFunding;
        private int userId;
        private int teamId;
        private int itemId;
        private int statusId;
        private LocalDate timestamp;
        private int paidAmount;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withIdFunding(int idFunding) {
            this.idFunding = idFunding;
            return this;
        }

        public Builder withUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder withTeamId(int teamId) {
            this.teamId = teamId;
            return this;
        }

        public Builder withItemId(int itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withStatusId(int statusId) {
            this.statusId = statusId;
            return this;
        }

        public Builder withTimestamp(LocalDate timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withPaidAmount(int paidAmount) {
            this.paidAmount = paidAmount;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}