package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MainView {
    private final PostView postView = new PostView();
    private final RegionView regionView = new RegionView();
    private final UserView userView = new UserView();

    public void start() throws IOException, ParseException {
        boolean exit = false;
        do {
            int i = 1;
            System.out.println("Введите номер файла, с которым желаете работать:");
            List files = Files.walk(Paths.get("src/main/resources/files"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            for (Object file : files) {
                System.out.println((i++) + ". " + file);
            }
            System.out.println("4. Завершить программу.");
            int choice = scanner().nextInt();
            switch (choice) {
                case 1 -> postView.start();
                case 2 -> regionView.start();
                case 3 -> userView.start();
                case 4 -> exit = true;
            }
        } while (!exit);
    }

    public Scanner scanner() {
        return new Scanner(System.in);
    }
}