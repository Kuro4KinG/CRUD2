package com.kuro4king.crud.repository;

import com.kuro4king.crud.model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getAll() throws IOException, ParseException;

    Post getById(Long id) throws IOException, ParseException;

    Post save(Post post) throws IOException, ParseException;

    Post update(Post post) throws IOException, ParseException;

    void delete(Long id) throws IOException, ParseException;
}
