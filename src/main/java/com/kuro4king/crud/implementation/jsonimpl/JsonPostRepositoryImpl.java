package com.kuro4king.crud.implementation.jsonimpl;

import com.google.gson.Gson;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.repository.PostRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class JsonPostRepositoryImpl implements PostRepository {
    @Override
    public List<Post> getAll() throws IOException, ParseException {
  return null;

    }

    @Override
    public Post getById(Long id) throws IOException, ParseException {
        return null;
    }

    @Override
    public Post save(Post post) throws IOException, ParseException {
        return null;
    }

    @Override
    public Post update(Post post) throws IOException, ParseException {
        return null;
    }

    @Override
    public void delete(Long id) throws IOException, ParseException {

    }
}
