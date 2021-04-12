package com.kuro4king.crud.controller;

import com.kuro4king.crud.implementation.ioimpl.JavaIOPostRepositoryImpl;

import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.repository.PostRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class PostController {
    private PostRepository postRepository = new JavaIOPostRepositoryImpl();

    public Post createPost(String content) throws IOException, ParseException {
        Post post = new Post(null, content, new Date(), new Date());
        return postRepository.save(post);
    }

    public List<Post> getAll() throws IOException, ParseException {
        return postRepository.getAll();
    }

    public void deletePost(Long id) throws IOException, ParseException {
        postRepository.delete(id);
    }

    public Post getPostById(Long id) throws IOException, ParseException {
        return postRepository.getById(id);
    }

    public Post updatePost(Long id, String name) throws IOException, ParseException {
        Post updatedPost = new Post(id, name, new Date(), new Date());
        return postRepository.update(updatedPost);
    }
}
