package com.kuro4king.crud.repository.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonPostRepositoryImpl implements PostRepository {
    private final String postPath = "src/main/resources/files/json/posts.json";
    Gson gson = new Gson();

    public List<Post> getAll() {
        try {
            return gson.fromJson(new FileReader(postPath), new TypeToken<ArrayList<Post>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getById(Long id) {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Post save(Post newPost) {
        List<Post> posts;
        posts = getAll();
        newPost = new Post(generateID(posts), newPost.getContent());
        posts.add(newPost);
        writeLines(this.postPath, posts);
        return newPost;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts;
        posts = getAll();

        Post updatedPost = posts.stream()
                .filter(el -> el.getId().equals(post.getId()))
                .findFirst().orElse(null);
        if (updatedPost != null) {
            updatedPost.setContent(post.getContent());
            updatedPost.setUpdated(new Date());
        }
        writeLines(postPath, posts);
        return updatedPost;
    }

    @Override
    public void delete(Long id) {
        List<Post> list;
        list = getAll();
        list.remove(list.stream().
                filter(el -> el.getId().equals(id))
                .findFirst().orElse(null));
        writeLines(postPath, list);
    }

    private void writeLines(String postPath, List<Post> posts) {
        try (FileWriter writer = new FileWriter(postPath)) {
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateID(List<Post> list) {
        return list.stream().map(Post::getId).max(Long::compare).orElse(null) + 1;
    }
}
