package com.codecool.quest_store.model;

public class Item {

    private final int ID;
    private int price;
    private String title;
    private String description;
    private String type;
    private String grade;

    private Item(Builder builder) {
        this.ID = builder.ID;
        this.price = builder.price;
        this.title = builder.title;
        this.description = builder.description;
        this.type = builder.type;
        this.grade = builder.grade;
    }

    public int getID() {
        return ID;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getGrade() {
        return grade;
    }

    public static class Builder {
        private int ID;
        private int price;
        private String title;
        private String description;
        private String type;
        private String grade;

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

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withGrade(String grade) {
            this.grade = grade;
            return this;
        }
    }
}