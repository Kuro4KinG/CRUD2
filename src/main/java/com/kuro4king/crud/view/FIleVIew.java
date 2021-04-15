package com.kuro4king.crud.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class FIleVIew extends ViewClass {
    HashMap<Integer, String> fileList = new HashMap<>();
    private int i;
    private int exitNumber;

    public void print() throws IOException, ParseException {
        i = 1;
        System.out.println("Введите номер файла, с которым желаете работать:");
        this.getAll();
        exitNumber = i;
        exitMessage(exitNumber);
    }

    public void choose() throws IOException, ParseException {
        int choice = scanner().nextInt();
        if (choice == exitNumber) {
            exit = true;
        } else {
            String file = fileList.get(choice);
            format = file.substring(file.lastIndexOf(".") + 1);
            String name = file.substring(0, file.lastIndexOf("."));

            switch (name) {
                case "posts" -> new PostView().start();
                case "regions" -> new RegionView().start();
                case "users" -> new UserView().start();
            }
        }
    }

    @Override
    protected void create() {
        System.out.println("Данный функционал не реализован");
    }

    @Override
    protected void getAll() throws IOException, ParseException {
        List<Path> files =
                Files.walk(Paths.get("src/main/resources/files"))
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList());

        for (Object file : files) {
            String fileName = new File(file.toString()).getName();
            System.out.println((i) + ". " + fileName);
            fileList.put(i++, fileName);
        }
    }

    @Override
    protected void delete() throws IOException, ParseException {
        System.out.println("Данный функционал не реализован");
    }

    @Override
    protected void getById() throws IOException, ParseException {
        System.out.println("Данный функционал не реализован");
    }

    @Override
    protected void update() throws IOException, ParseException {
        System.out.println("Данный функционал не реализован");
    }
}