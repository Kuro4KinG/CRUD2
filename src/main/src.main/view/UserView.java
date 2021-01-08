package view;

import controller.PostController;
import controller.RegionController;
import controller.UserController;
import model.Post;
import model.Region;
import model.Role;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserView {
    private final UserController userController = new UserController();

    public void start() throws IOException, ParseException {
        boolean exit = false;
        do {
            System.out.println("Введите номер действия, которые желаете произвести:");
            System.out.println("1. Вывести список всех пользователей.");
            System.out.println("2. Вывести информацию о пользователе по заданному id.");
            System.out.println("3. Добавить нового пользователя.");
            System.out.println("4. Удалить пользователя.");
            System.out.println("5. Изменить информацию о пользователе.");
            System.out.println("6. Вернуться к выбору файла");
            System.out.println("7. Завершить программу.");

            int choice = scanner().nextInt();
            switch (choice) {
                case 1 -> this.getAllUsers();
                case 2 -> this.getUserById();
                case 3 -> this.createUser();
                case 4 -> this.deleteUser();
                case 5 -> this.updateUser();
                case 6 -> exit = true;
                case 7 -> System.exit(0);
            }
        } while (!exit);
    }

    public Scanner scanner() {
        return new Scanner(System.in);
    }

    public void createUser() throws IOException, ParseException {
        System.out.println("Введите имя пользователя:");
        String userFName = scanner().nextLine();
        System.out.println("Введите фамилию пользователя:");
        String userLName = scanner().nextLine();
        System.out.println("Введите список id постов пользователя через запятую (Например `1,2`:");
        String post = scanner().nextLine();
        List<Post> listOfPosts = Arrays.stream(post.split(",")).map(el -> {
            try {
                return new PostController().getPostById(Long.parseLong(el));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        System.out.println("Выберите регион пользователя: ");
        System.out.println(new RegionController().getAll());
        Region region = new RegionController().getRegionById(Long.parseLong(scanner().nextLine()));
        System.out.println("Введите роль пользователя из перечисленных вариантов");
        System.out.println(Arrays.toString(Role.values()));
        Role role = Role.valueOf(scanner().nextLine());
        User newUser = userController.createUser(userFName, userLName, listOfPosts, region, role);
        System.out.println("Вы добавили нового пользователя:");
        System.out.println(newUser);
    }

    public void getAllUsers() throws IOException, ParseException {
        System.out.println("Список пользователей:");
        userController.getAll().forEach(System.out::println);
    }

    public void deleteUser() throws IOException, ParseException {
        this.getAllUsers();
        System.out.println("Введите id пользователя, которого желаете удалить:");
        Long id = scanner().nextLong();
        User deletedUser = userController.getUserById(id);
        userController.deleteUser(id);
        System.out.println("Удалён пользователь " + deletedUser);
        this.getAllUsers();
    }

    public void getUserById() throws IOException, ParseException {
        System.out.println("Введите id пользователя, который желаете получить:");
        Long id = scanner().nextLong();
        User user = userController.getUserById(id);
        System.out.println("Запрашиваемый пользователь: ");
        System.out.println(user);
    }

    public void updateUser() throws IOException, ParseException {
        System.out.println("Введите id пользователя, которого хотите изменить:");
        Long id = scanner().nextLong();
        System.out.println("Введите новое имя пользователя:");
        String firstName = scanner().nextLine();

        System.out.println("Введите новую фамилию пользователя:");
        String lastName = scanner().nextLine();
        System.out.println("Введите новый список id постов пользователя через запятую (Например `1,2`:");
        String post = scanner().nextLine();
        List<Post> listOfPosts = Arrays.stream(post.split(",")).map(el -> {
            try {
                return new PostController().getPostById(Long.parseLong(el));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        System.out.println("Выберите новый регион пользователя: ");
        System.out.println(new RegionController().getAll());
        Region region = new RegionController().getRegionById(Long.parseLong(scanner().nextLine()));
        System.out.println("Введите новую роль пользователя из перечисленных вариантов");
        System.out.println(Arrays.toString(Role.values()));
        Role role = Role.valueOf(scanner().nextLine());
        userController.updateUser(id, firstName, lastName, listOfPosts, region, role);
        this.getAllUsers();
    }
}
