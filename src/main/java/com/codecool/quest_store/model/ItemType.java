package com.codecool.quest_store.model;

public enum ItemType {

    NORMAL(1),
    MAGIC(2),
    BASIC(3),
    EXTRA(4);

    private int itemType;

    private ItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return this.itemType;
    }
}

