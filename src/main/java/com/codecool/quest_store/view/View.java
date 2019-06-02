package com.codecool.quest_store.view;

public class View {

    private void print(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        System.err.println(message);
    }

    public void printSuccess(String message) {
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";
        print(GREEN + message + RESET);
    }
}