package com.kuro4king.crud.implementation.jsonimpl;

import com.kuro4king.crud.model.User;
import com.kuro4king.crud.repository.UserRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class JsonUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) throws IOException, ParseException {
        return null;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException {
        return null;
    }

    @Override
    public User getById(Long id) throws IOException, ParseException {
        return null;
    }

    @Override
    public User update(User user) throws IOException, ParseException {
        return null;
    }

    @Override
    public void delete(Long id) throws IOException, ParseException {

    }
}
