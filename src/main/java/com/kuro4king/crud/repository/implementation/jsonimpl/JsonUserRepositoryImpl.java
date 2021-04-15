package com.kuro4king.crud.repository.implementation.jsonimpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.model.Role;
import com.kuro4king.crud.model.User;
import com.kuro4king.crud.repository.UserRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUserRepositoryImpl implements UserRepository {
    private final String userPath = "src/main/resources/files/json/users.json";
    Gson gson = new Gson();

    @Override
    public User save(User newUser) {
        List<User> users = getAll();
        newUser.setId(generateID(users));
        users.add(newUser);
        writeLines(users);
        return newUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        JsonArray jsonArray = null;
        try {
            jsonArray = gson.fromJson(new FileReader(userPath), JsonArray.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (jsonArray != null) {
            for (JsonElement obj :
                    jsonArray) {
                JsonObject jsonObject = obj.getAsJsonObject();
                Long id = jsonObject.get("id").getAsLong();
                String firstName = jsonObject.get("firstName").getAsString();
                String lastName = jsonObject.get("lastName").getAsString();
                List<Post> posts = gson.fromJson(jsonObject.get("posts"), new TypeToken<ArrayList<Post>>() {
                }.getType());
                Region region = gson.fromJson(jsonObject.get("region"), Region.class);
                Role role = Role.valueOf(jsonObject.get("role").getAsString());

                users.add(new User(id, firstName, lastName, posts, region, role));
            }
        }
        return users;
    }

    @Override
    public User getById(Long id) {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public User update(User user) {
        List<User> users = getAll();

        User updatedUser = users.stream()
                .filter(el -> el.getId().equals(user.getId()))
                .findFirst().orElse(null);
        if (updatedUser != null) {
            updatedUser.setRole(user.getRole());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPosts(user.getPosts());
            updatedUser.setRegion(user.getRegion());
        }
        writeLines(users);
        return updatedUser;
    }

    @Override
    public void delete(Long id) {
        List<User> users = getAll();
        users.remove(users.stream().
                filter(el -> el.getId().equals(id))
                .findFirst().orElse(null));
        writeLines(users);
    }

    private void writeLines(List<User> users) {

        try (FileWriter writer = new FileWriter(userPath)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateID(List<User> list) {
        return list.stream().map(User::getId).max(Long::compare).orElse(null) + 1;
    }
}
