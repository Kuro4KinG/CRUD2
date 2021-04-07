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
    private final PostView postView = new PostView();
    private final RegionView regionView = new RegionView();
    private final UserView userView = new UserView();

    public void start() throws IOException, ParseException {
        HashMap<Integer, String> fileList = new HashMap<>();
        boolean exit = false;
        int exitNumber;
        int i = 1;
        int choice;

        //     do {

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
        System.out.println(exitNumber + ". Завершить программу.");
        choice = scanner().nextInt();
        String file = fileList.get(choice);
        String format = file.substring(file.lastIndexOf(".") + 1);
        System.out.println(format);
        String name = file.substring(0, file.lastIndexOf("."));
        System.out.println(name);

//            switch (choice) {
//                case 1 -> postView.start();
//                case 2 -> regionView.start();
//                case 3 -> userView.start();
//                case 4 -> exit = true;
//            }


        //     } while (!exit);
    }

}