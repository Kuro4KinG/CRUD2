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

public class JsonPostRepositoryImpl implements PostRepository {
    final String postPath = "src/main/resources/files/json/posts.json";
    Gson gson = new Gson();

    @Override
    public List<Post> getAll() throws IOException, ParseException {
        FileReader fileReader = new FileReader(postPath);
        List<Post> posts = gson.fromJson(fileReader, new TypeToken<ArrayList<Post>>() {
        }.getType());
        fileReader.close();
        return posts;

    }

    @Override
    public Post getById(Long id) throws IOException, ParseException {
        List<Post> posts = getAll();
        for (Post post :
                posts) {
            if (post.getId().equals(id))
                return post;
        }
        return null;
    }

    @Override
    public Post save(Post post) throws IOException, ParseException {
        return null;
    }

    @Override
    public Post update(Post post) throws IOException, ParseException {
        List<Post> posts = getAll();
        for (Post p :
                posts) {
            if (p.getId().equals(post.getId())) {
                p.setContent(post.getContent());
                p.setUpdated(new Date());
            }
        }
        writeLines(postPath, posts);
        return post;
    }

    @Override
    public void delete(Long id) throws IOException, ParseException {
        List<Post> posts = getAll();
        Post postToDelete = null;
        for (Post p :
                posts) {
            if (p.getId().equals(id))
                postToDelete = p;
        }
        posts.remove(postToDelete);
        writeLines(postPath, posts);
    }

    private void writeLines(String postPath, List<Post> posts) throws IOException {
        FileWriter writer = new FileWriter(postPath);
        gson.toJson(posts, writer);
        writer.close();
    }
}
