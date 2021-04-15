package com.kuro4king.crud.repository;

import com.kuro4king.crud.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getAll();

    Post getById(Long id);

    Post save(Post post);

    Post update(Post post);

    void delete(Long id);
}
