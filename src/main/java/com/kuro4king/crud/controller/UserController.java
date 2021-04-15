package com.kuro4king.crud.controller;

import com.kuro4king.crud.repository.implementation.ioimpl.JavaIOUserRepositoryImpl;
import com.kuro4king.crud.repository.implementation.jsonimpl.JsonUserRepositoryImpl;
import com.kuro4king.crud.repository.UserRepository;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.model.Role;
import com.kuro4king.crud.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserController {
    private UserRepository userRepository;

    public UserController(String format) {
        if (format.equals("json")) {
            userRepository = new JsonUserRepositoryImpl();
        } else if (format.equals("txt")) {
            userRepository = new JavaIOUserRepositoryImpl();
        }
    }


    public User createUser(String firstName, String lastName, List<Post> posts, Region region, Role role) {
        User user = new User(null, firstName, lastName, posts, region, role);

        return userRepository.save(user);
    }

    public List<User> getAll() throws IOException, ParseException {
        return userRepository.getAll();
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User updateUser(Long id, String firstName, String lastName, List<Post> posts, Region region, Role role) {
        User updatedUser = new User(id, firstName, lastName, posts, region, role);
        return userRepository.update(updatedUser);
    }
}
