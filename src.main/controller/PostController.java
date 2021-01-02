package controller;

import Implementation.PostRepositoryImpl;
import model.Post;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PostController {
    private PostRepositoryImpl postRepositoryImpl = new PostRepositoryImpl();

    public Post createPost(String content) throws IOException {
        Post post = new Post(null, content, new Date(),new Date());
        return postRepositoryImpl.save(post);
    }

    public List<Post> getAll() throws IOException {
        return postRepositoryImpl.getAll();
    }

    public void deletePost(Long id) throws IOException {
        postRepositoryImpl.delete(id);
    }

    public Post getPostById(Long id) throws IOException {
        return postRepositoryImpl.getById(id);
    }

    public Post updatePost(Long id, String name) throws IOException {
        Post updatedPost = new Post(id, name,);
        return postRepositoryImpl.update(updatedPost);
    }
}
