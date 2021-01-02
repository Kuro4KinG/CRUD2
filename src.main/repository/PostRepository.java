package repository;

import model.Post;

import java.io.IOException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getAll() throws IOException;

    Post getById(Long id) throws IOException;

    Post save(Post post) throws IOException;

    Post update(Post post) throws IOException;

    void delete(Long id) throws IOException;
}
