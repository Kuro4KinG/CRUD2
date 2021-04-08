package com.kuro4king.crud.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class FIleVIew extends ViewClass {
    HashMap<Integer, String> fileList = new HashMap<>();
    int i;
    int exitNumber;
    boolean exit = false;
    int choice;

    public void start() throws IOException, ParseException {
        do {
            print();
            choose();
        } while (!exit);
    }

    private void print() throws IOException {
        i = 1;
        System.out.println("Введите номер файла, с которым желаете работать:");
        List files =
                Files.walk(Paths.get("src/main/resources/files"))
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList());

        for (Object file : files) {
            String fileName = new File(file.toString()).getName();
            System.out.println((i) + ". " + fileName);
            fileList.put(i++, fileName);
        }
        exitNumber = i;
        exitMessage(exitNumber);
    }

    private void choose() throws IOException, ParseException {
        choice = scanner().nextInt();
        if (choice == exitNumber) {
            exit = true;
        } else {
            String file = fileList.get(choice);
            String format = file.substring(file.lastIndexOf(".") + 1);
            String name = file.substring(0, file.lastIndexOf("."));

            switch (name) {
                case "posts" -> new PostView().start();
                case "regions" -> new RegionView().start();
                case "users" -> new UserView().start();
            }
        }
    }

}