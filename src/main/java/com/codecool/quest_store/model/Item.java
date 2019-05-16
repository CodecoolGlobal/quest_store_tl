package com.codecool.quest_store.model;

public class Item {

    private final int ID;
    private int price;
    private String title;
    private String description;
    private int type;

    private Item(Builder builder) {
        this.ID = builder.ID;
        this.price = builder.price;
        this.title = builder.title;
        this.description = builder.description;
        this.type = builder.type;
    }

    public int getID() {
        return ID;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountedPrice(float discount){
        return Math.round(price * (1-discount));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }


    public static class Builder {
        private int ID;
        private int price;
        private String title;
        private String description;
        private int type;

        public Builder withID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}