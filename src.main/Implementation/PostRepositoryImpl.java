package Implementation;
import model.Post;

import repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepositoryImpl implements PostRepository {
    final String posts = "src.main/files/posts.txt";
    @Override
    public List<Post> getAll() throws IOException {
        return
                getLines(posts).
                stream().map(line -> List.of(line.split(". ")))
                .map(this::postFromLine)
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(Long id) throws IOException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Post save(Post newPost) throws IOException {
        List<Post> list = getAll();
        newPost = new Post(generateID(list), newPost.getContent());
        list.add(newPost);
        writeLines(posts, list);
        return newPost;
    }

    @Override
    public Post update(Post post) throws IOException {
        List<Post> list = getAll();

        Post updatedPost = list.stream()
                .filter(el -> el.getId().equals(post.getId()))
                .findFirst().get();
        updatedPost.setContent(post.getContent());
        updatedPost.setUpdated(new Date());
        writeLines(posts, list);
        return updatedPost;
    }

    @Override
    public void delete(Long id) throws IOException {
        List<Post> list = getAll();
        list.remove(list.stream().
                filter(el -> el.getId().equals(id))
                .collect(Collectors.toList()).get(0));
        writeLines(posts, list);
    }
    private List<String> getLines(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.toList());
    }

    private void writeLines(String path, List<Post> list) throws IOException {
        List newList = list.stream()
                .map(Post::toString)
                .collect(Collectors.toList());
        Files.write(Paths.get(path), newList);
    }

    private Post postFromLine(List<String> line) {

       return new Post(Long.parseLong(line.get(0)), line.get(1));
    }

    private Long generateID(List<Post> list) {
        return list.stream().map(Post::getId).max(Long::compare).get() + 1;

    }
}
