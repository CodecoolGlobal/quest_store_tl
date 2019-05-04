package com.codecool.quest_store.model;

public class Funding {

    private final int ID;
    private final int ITEM_ID;
    private final int TEAM_ID;

    private Funding(Builder builder) {
        this.ID = builder.ID;
        this.ITEM_ID = builder.ITEM_ID;
        this.TEAM_ID = builder.TEAM_ID;
    }

    public int getID() {
        return ID;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public static class Builder {
        private int ID;
        private int ITEM_ID;
        private int TEAM_ID;

        public Builder withID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder withITEM_ID(int ITEM_ID) {
            this.ITEM_ID = ITEM_ID;
            return this;
        }

        public Builder withTEAM_ID(int TEAM_ID) {
            this.TEAM_ID = TEAM_ID;
            return this;
        }

        public Funding build() {
            return new Funding(this);
        }
    }
}