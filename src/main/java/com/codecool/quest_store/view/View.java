package com.codecool.quest_store.view;

public class View {

    private final String RESET = "\u001B[0m";

    private void print(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        final String RED = "\u001B[31m";
        print(RED + message + RESET);
    }

    public void printSuccess(String message) {
        final String GREEN = "\u001B[32m";
        print(GREEN + message + RESET);
    }
}