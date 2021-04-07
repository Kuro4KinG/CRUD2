package com.kuro4king.crud.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public abstract class ViewClass implements View {
    @Override
    public abstract void start() throws IOException, ParseException;
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
