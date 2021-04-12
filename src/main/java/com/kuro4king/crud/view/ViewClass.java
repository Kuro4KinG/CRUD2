package com.kuro4king.crud.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public abstract class ViewClass {
    boolean exit = false;

    protected abstract void print() throws IOException, ParseException;

    protected abstract void choose() throws IOException, ParseException;

    protected abstract void create() throws IOException, ParseException;

    protected abstract void getAll() throws IOException, ParseException;

    protected abstract void delete() throws IOException, ParseException;

    protected abstract void getById() throws IOException, ParseException;

    protected abstract void update() throws IOException, ParseException;

    public void start() throws IOException, ParseException {
        do {
            print();
            choose();
        } while (!exit);
    }

    public Scanner scanner() {
        return new Scanner(System.in);
    }

    public void exitMessage(int exitN) {
        System.out.println(exitN + ". Завершить программу.");
    }

}
