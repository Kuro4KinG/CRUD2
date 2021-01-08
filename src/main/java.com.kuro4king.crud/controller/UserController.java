package controller;

import implementation.JavaIOUserRepositoryImpl;
import repository.UserRepository;
import model.Post;
import model.Region;
import model.Role;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserController {
    private UserRepository userRepository = new JavaIOUserRepositoryImpl();

    public User createUser(String firstName, String lastName, List<Post> posts, Region region, Role role) throws IOException, ParseException {
        User user = new User(null, firstName, lastName, posts, region, role);
        return userRepository.save(user);
    }

    public List<User> getAll() throws IOException, ParseException {
        return userRepository.getAll();
    }

    public void deleteUser(Long id) throws IOException, ParseException {
        userRepository.delete(id);
    }

    public User getUserById(Long id) throws IOException, ParseException {
        return userRepository.getById(id);
    }

    public User updateUser(Long id, String firstName, String lastName, List<Post> posts, Region region, Role role) throws IOException, ParseException {
        User updatedUser = new User(id, firstName, lastName, posts, region, role);
        return userRepository.update(updatedUser);
    }
}
