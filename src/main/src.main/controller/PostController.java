package controller;

import implementation.JavaIOPostRepositoryImpl;

import model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class PostController {
    private JavaIOPostRepositoryImpl postRepositoryImpl = new JavaIOPostRepositoryImpl();

    public Post createPost(String content) throws IOException, ParseException {
        Post post = new Post(null, content, new Date(), new Date());
        return postRepositoryImpl.save(post);
    }

    public List<Post> getAll() throws IOException, ParseException {
        return postRepositoryImpl.getAll();
    }

    public void deletePost(Long id) throws IOException, ParseException {
        postRepositoryImpl.delete(id);
    }

    public Post getPostById(Long id) throws IOException, ParseException {
        return postRepositoryImpl.getById(id);
    }

    public Post updatePost(Long id, String name) throws IOException, ParseException {
        Post updatedPost = new Post(id, name, new Date(), new Date());
        return postRepositoryImpl.update(updatedPost);
    }
}
