package repository;

import model.Region;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface UserRepository extends GenericRepository<User, Long> {
    User save(User user) throws IOException, ParseException;

    List<User> getAll() throws IOException, ParseException;

    User getById(Long id) throws IOException, ParseException;

    User update(User user) throws IOException, ParseException;

    void delete(Long id) throws IOException, ParseException;
}
