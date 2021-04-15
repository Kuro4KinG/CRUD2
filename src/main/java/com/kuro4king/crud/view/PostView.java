package com.kuro4king.crud.view;

import com.kuro4king.crud.controller.PostController;
import com.kuro4king.crud.model.Post;

import java.io.IOException;
import java.text.ParseException;

public class PostView extends ViewClass {

    private final PostController postController = new PostController(format);


    protected void print() {
        System.out.println("Введите номер действия, которые желаете произвести:");
        System.out.println("1. Вывести список всех постов.");
        System.out.println("2. Вывести пост по заданному id.");
        System.out.println("3. Добавить новый пост.");
        System.out.println("4. Удалить пост.");
        System.out.println("5. Изменить содержимое поста.");
        System.out.println("6. Вернуться к выбору файла");
        exitMessage(7);

    }

    protected void choose() throws IOException, ParseException {
        int choice = scanner().nextInt();
        switch (choice) {
            case 1 -> this.getAll();
            case 2 -> this.getById();
            case 3 -> this.create();
            case 4 -> this.delete();
            case 5 -> this.update();
            case 6 -> super.exit = true;
            case 7 -> System.exit(0);

        }
    }

    public void create() throws IOException, ParseException {
        System.out.println("Введите содержимое поста:");
        String postContent = scanner().nextLine();
        Post post = postController.createPost(postContent);
        System.out.println("Вы добавили новый пост: " + post);
    }

    public void getAll() throws IOException, ParseException {
        System.out.println("Список постов:");
        System.out.println(postController.getAll());
    }

    public void delete() throws IOException, ParseException {
        this.getAll();
        System.out.println("Введите id поста, который желаете удалить:");
        Long id = scanner().nextLong();
        Post deletedPost = postController.getPostById(id);
        postController.deletePost(id);
        System.out.println("Удалён пост " + deletedPost);
        this.getAll();
    }

    public void getById() throws IOException, ParseException {
        System.out.println("Введите id поста, который желаете получить:");
        Long id = scanner().nextLong();
        Post post = postController.getPostById(id);
        System.out.println("Запрашиваемый пост: " + post);
    }

    public void update() throws IOException, ParseException {
        System.out.println("Введите id поста, содержимое которого хотите изменить:");
        Long id = scanner().nextLong();
        System.out.println("Введите новое содержимое поста:");
        String content = scanner().nextLine();
        postController.updatePost(id, content);
        this.getAll();
    }
}
