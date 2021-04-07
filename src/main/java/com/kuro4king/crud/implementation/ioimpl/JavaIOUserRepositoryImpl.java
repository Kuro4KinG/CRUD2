package com.kuro4king.crud.implementation.ioimpl;

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
import java.util.stream.Collectors;

public class JavaIOUserRepositoryImpl implements UserRepository {
    final String users = "src/main/resources/files/txt/users.txt";

    @Override
    public List<User> getAll() throws IOException, ParseException {
        return
                getLines(users).
                        stream()
                        .map(line1 -> {
                            try {
                                return userFromLine(line1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());
    }

    @Override
    public User getById(Long id) throws IOException, ParseException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public User save(User newUser) throws IOException, ParseException {
        List<User> list = getAll();
        newUser.setId(generateID(list));
        list.add(newUser);
        writeLines(users, list);
        return newUser;
    }

    @Override
    public User update(User user) throws IOException, ParseException {
        List<User> list = getAll();

        User updatedUser = list.stream()
                .filter(el -> el.getId().equals(user.getId()))
                .findFirst().get();
        updatedUser.setRole(user.getRole());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPosts(user.getPosts());
        updatedUser.setRegion(user.getRegion());
        writeLines(users, list);
        return updatedUser;
    }

    @Override
    public void delete(Long id) throws IOException, ParseException {
        List<User> list = getAll();
        list.remove(list.stream().
                filter(el -> el.getId().equals(id))
                .collect(Collectors.toList()).get(0));
        writeLines(users, list);
    }

    private List<String> getLines(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.toList());
    }

    private void writeLines(String path, List<User> list) throws IOException {
        List newList = list.stream()
                .map(User::toString)
                .collect(Collectors.toList());
        Files.write(Paths.get(path), newList);
    }

    private User userFromLine(String line) throws IOException {
        String[] splitLine = line.split(", |. ");
        String post = splitLine[3].substring(1, splitLine[3].length() - 1);
        List<Post> posts = Arrays.stream(post.split(",")).map(el -> {
            try {
                return new PostController().getPostById(Long.parseLong(el));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        Region region = new RegionController().getRegionByName(splitLine[4]);
        Role role = Role.valueOf(splitLine[5]);
        return new User(Long.parseLong(splitLine[0]), splitLine[1], splitLine[2], posts, region, role);
    }

    private Long generateID(List<User> list) {
        return list.stream().map(User::getId).max(Long::compare).get() + 1;

    }
}
