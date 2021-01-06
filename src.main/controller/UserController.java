package controller;

import Implementation.UserRepositoryImpl;
import model.Post;
import model.Region;
import model.Role;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserController {
    private UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();

    public User createUser(String firstName, String lastName, List<Post> posts, Region region, Role role) throws IOException, ParseException {
        User user = new User(null, firstName, lastName, posts, region, role);
        return userRepositoryImpl.save(user);
    }

    public List<User> getAll() throws IOException, ParseException {
        return userRepositoryImpl.getAll();
    }

    public void deleteUser(Long id) throws IOException, ParseException {
        userRepositoryImpl.delete(id);
    }

    public User getUserById(Long id) throws IOException, ParseException {
        return userRepositoryImpl.getById(id);
    }

    public User updateUser(Long id, String firstName, String lastName, List<Post> posts, Region region, Role role) throws IOException, ParseException {
        User updatedUser = new User(id, firstName, lastName, posts, region, role);
        return userRepositoryImpl.update(updatedUser);
    }
}
