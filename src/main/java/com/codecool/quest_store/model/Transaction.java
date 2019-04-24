package com.codecool.quest_store.model;

import java.time.LocalDate;

import com.codecool.quest_store.model.Team;
import com.codecool.quest_store.model.TransactionStatus;
import com.codecool.quest_store.model.User;

public class Transaction {

    private int id;
    private User user;
    private Team team;
    private Enum status;
    private LocalDate timestamp;
    private int paidAmount;

    public Transaction(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.team = builder.team;
        this.status = builder.status;
        this.timestamp = builder.timestamp;
        this.paidAmount = builder.paidAmount;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }

    public Enum getStatus() {
        return status;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public static class Builder {
        private int id;
        private User user;
        private Team team;
        private Enum status;
        private LocalDate timestamp;
        private int paidAmount;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withTeam(Team team) {
            this.team = team;
            return this;
        }

        public Builder withStatus(Enum status) {
            this.status = status;
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