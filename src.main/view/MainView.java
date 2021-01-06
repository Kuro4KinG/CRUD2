package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainView {
    public MainView() throws IOException, ParseException {
        System.out.println("Введите номер файла, с которым желаете работать:");
        List files = Files.walk(Paths.get("src.main/files"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        int i = 1;
        for (Object file : files) {
            System.out.println((i++) + " " + file);
        }
        int choice = scanner().nextInt();
        switch (choice) {
            case 1 -> new PostView();
            case 2 -> new RegionView();
            case 3 -> new UserView();
        }
    }

    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
