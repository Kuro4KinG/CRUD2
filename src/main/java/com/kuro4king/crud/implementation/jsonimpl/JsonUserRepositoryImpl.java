package com.kuro4king.crud.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.model.User;
import com.kuro4king.crud.repository.UserRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUserRepositoryImpl implements UserRepository {
    final String userPath = "src/main/resources/files/json/users.json";
    Gson gson = new Gson();

    @Override
    public User save(User newUser) throws IOException, ParseException {
        List<User> users = getAll();
        newUser.setId(generateID(users));
        users.add(newUser);
        writeLines(userPath, users);
        return newUser;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException {
        return gson.fromJson(new FileReader(userPath), new TypeToken<ArrayList<User>>() {
        }.getType());
    }

    @Override
    public User getById(Long id) throws IOException, ParseException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public User update(User user) throws IOException, ParseException {
        List<User> users = getAll();

        User updatedUser = users.stream()
                .filter(el -> el.getId().equals(user.getId()))
                .findFirst().get();
        updatedUser.setRole(user.getRole());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPosts(user.getPosts());
        updatedUser.setRegion(user.getRegion());
        writeLines(userPath, users);
        return updatedUser;
    }

    @Override
    public void delete(Long id) throws IOException, ParseException {
        List<User> users = getAll();
        users.remove(users.stream().
                filter(el -> el.getId().equals(id))
                .collect(Collectors.toList()).get(0));
        writeLines(userPath, users);
    }

    private void writeLines(String regionPath, List<User> users) throws IOException {
        FileWriter writer = new FileWriter(regionPath);
        gson.toJson(users, writer);
        writer.close();
    }

    private Long generateID(List<User> list) {
        return list.stream().map(User::getId).max(Long::compare).get() + 1;
    }
}
