package com.kuro4king.crud.repository.implementation.ioimpl;

import com.kuro4king.crud.model.Post;

import com.kuro4king.crud.repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class JavaIOPostRepositoryImpl implements PostRepository {
    private final String postPath = "src/main/resources/files/txt/posts.txt";

    @Override
    public List<Post> getAll() {
        List<Post> posts;
        posts = Objects.requireNonNull(getLines()).
                stream().map(line -> {
            try {
                return postFromLine(line);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        })
                .collect(Collectors.toList());
        return posts;
    }

    @Override
    public Post getById(Long id) {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Post save(Post newPost) {
        List<Post> posts = getAll();
        newPost = new Post(generateID(posts), newPost.getContent());
        posts.add(newPost);
        writeLines(this.postPath, posts);
        return newPost;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts = getAll();

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
        List<Post> list = getAll();
        list.remove(list.stream().
                filter(el -> el.getId().equals(id))
                .findFirst().orElse(null));
        writeLines(postPath, list);
    }

    private List<String> getLines() {
        try {
            return Files.lines(Paths.get(postPath)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeLines(String path, List<Post> list) {
        List<String> newList = list.stream()
                .map(Post::write)
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(path), newList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Post postFromLine(String line) throws ParseException {
        String[] splitLine = line.split("\\. | Дата создания: | Дата изменения: ");
        SimpleDateFormat df = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
        Post newPost = new Post(Long.parseLong(splitLine[0]), splitLine[1]);
        newPost.setCreated(df.parse(splitLine[2]));
        newPost.setUpdated(df.parse(splitLine[3]));
        return newPost;
    }

    private Long generateID(List<Post> list) {
        return list.stream().map(Post::getId).max(Long::compare).orElse(null) + 1;
    }
}
