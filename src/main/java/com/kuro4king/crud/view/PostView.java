package com.kuro4king.crud.view;

import com.kuro4king.crud.controller.PostController;
import com.kuro4king.crud.model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class PostView {
    private PostController postController = new PostController();

    public void start() throws IOException, ParseException {
        boolean exit = false;
        do {
            System.out.println("Введите номер действия, которые желаете произвести:");
            System.out.println("1. Вывести список всех постов.");
            System.out.println("2. Вывести пост по заданному id.");
            System.out.println("3. Добавить новый пост.");
            System.out.println("4. Удалить пост.");
            System.out.println("5. Изменить содержимое поста.");
            System.out.println("6. Вернуться к выбору файла");
            System.out.println("7. Завершить программу.");

            int choice = scanner().nextInt();
            switch (choice) {
                case 1 -> this.getAllPosts();
                case 2 -> this.getPostById();
                case 3 -> this.createPost();
                case 4 -> this.deletePost();
                case 5 -> this.updatePost();
                case 6 -> exit = true;
                case 7 -> System.exit(0);

            }
        } while (!exit);
    }

    public Scanner scanner() {
        return new Scanner(System.in);
    }

    public void createPost() throws IOException, ParseException {
        System.out.println("Введите содержимое поста:");
        String postContent = scanner().nextLine();
        Post post = postController.createPost(postContent);
        System.out.println("Вы добавили новый пост: " + post);
    }

    public void getAllPosts() throws IOException, ParseException {
        System.out.println("Список постов:");
        System.out.println(postController.getAll());
    }

    public void deletePost() throws IOException, ParseException {
        this.getAllPosts();
        System.out.println("Введите id поста, который желаете удалить:");
        Long id = scanner().nextLong();
        Post deletedPost = postController.getPostById(id);
        postController.deletePost(id);
        System.out.println("Удалён пост " + deletedPost);
        this.getAllPosts();
    }

    public void getPostById() throws IOException, ParseException {
        System.out.println("Введите id поста, который желаете получить:");
        Long id = scanner().nextLong();
        Post post = postController.getPostById(id);
        System.out.println("Запрашиваемый пост: " + post);
    }

    public void updatePost() throws IOException, ParseException {
        System.out.println("Введите id поста, содержимое которого хотите изменить:");
        Long id = scanner().nextLong();
        System.out.println("Введите новое содержимое поста:");
        String content = scanner().nextLine();
        postController.updatePost(id, content);
        this.getAllPosts();
    }
}
