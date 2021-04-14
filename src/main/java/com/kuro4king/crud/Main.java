package com.kuro4king.crud;

import com.kuro4king.crud.implementation.jsonimpl.JsonPostRepositoryImpl;
import com.kuro4king.crud.view.FIleVIew;

import java.io.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        new FIleVIew().start();
      //  System.out.println( new JsonPostRepositoryImpl().getAll());
    }
}
