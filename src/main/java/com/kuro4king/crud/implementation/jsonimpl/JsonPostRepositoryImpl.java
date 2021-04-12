package com.kuro4king.crud.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.repository.PostRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPostRepositoryImpl implements PostRepository {
    final String postPath = "src/main/resources/files/json/posts.json";
    Gson gson = new Gson();

    @Override
    public List<Post> getAll() throws IOException, ParseException {
        return gson.fromJson(new FileReader(postPath), new TypeToken<ArrayList<Post>>() {
        }.getType());

    }

    @Override
    public Post getById(Long id) throws IOException, ParseException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Post save(Post newPost) throws IOException, ParseException {
        List<Post> posts = getAll();
        newPost = new Post(generateID(posts), newPost.getContent());
        posts.add(newPost);
        writeLines(this.postPath, posts);
        return newPost;
    }

    @Override
    public Post update(Post post) throws IOException, ParseException {
        List<Post> posts = getAll();

        Post updatedPost = posts.stream()
                .filter(el -> el.getId().equals(post.getId()))
                .findFirst().get();
        updatedPost.setContent(post.getContent());
        updatedPost.setUpdated(new Date());
        writeLines(postPath, posts);
        return updatedPost;
    }

    @Override
    public void delete(Long id) throws IOException, ParseException {
        List<Post> list = getAll();
        list.remove(list.stream().
                filter(el -> el.getId().equals(id))
                .collect(Collectors.toList()).get(0));
        writeLines(postPath, list);
    }

    private void writeLines(String postPath, List<Post> posts) throws IOException {
        FileWriter writer = new FileWriter(postPath);
        gson.toJson(posts, writer);
        writer.close();
    }

    private Long generateID(List<Post> list) {
        if (!(list == null))
            return list.stream().map(Post::getId).max(Long::compare).get() + 1;
        else return null;
    }
}
