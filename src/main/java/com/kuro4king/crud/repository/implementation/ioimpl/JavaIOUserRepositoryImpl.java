package com.kuro4king.crud.repository.implementation.ioimpl;

import com.kuro4king.crud.controller.PostController;
import com.kuro4king.crud.controller.RegionController;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.model.Role;
import com.kuro4king.crud.model.User;
import com.kuro4king.crud.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JavaIOUserRepositoryImpl implements UserRepository {
    private final String userPath = "src/main/resources/files/txt/users.txt";

    @Override
    public List<User> getAll() {
        return
                Objects.requireNonNull(getLines()).
                        stream()
                        .map(this::userFromLine)
                        .collect(Collectors.toList());
    }

    @Override
    public User getById(Long id) {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public User save(User newUser) {
        List<User> users = getAll();
        newUser.setId(generateID(users));
        users.add(newUser);
        writeLines(users);
        return newUser;
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

    private List<String> getLines() {
        try {
            return Files.lines(Paths.get(userPath)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeLines(List<User> list) {
        List<String> newList = list.stream()
                .map(User::toString)
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(userPath), newList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User userFromLine(String line) {
        String[] splitLine = line.split(", |. ");
        String post = splitLine[3].substring(1, splitLine[3].length() - 1);
        List<Post> posts = Arrays.stream(post.split(",")).map(el -> {
            try {
                return new PostController("txt").getPostById(Long.parseLong(el));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        Region region = null;
        try {
            region = new RegionController("txt").getRegionByName(splitLine[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Role role = Role.valueOf(splitLine[5]);
        return new User(Long.parseLong(splitLine[0]), splitLine[1], splitLine[2], posts, region, role);
    }

    private Long generateID(List<User> list) {
        return list.stream().map(User::getId).max(Long::compare).orElse(null) + 1;
    }
}
