package Implementation;

import model.Post;

import repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PostRepositoryImpl implements PostRepository {
    final String posts = "src.main/files/posts.txt";

    @Override
    public List<Post> getAll() throws IOException, ParseException {
        return
                getLines(posts).
                        stream().map(line -> List.of(line.split("\\. | Дата создания: | Дата изменения: ")))
                        .map(line1 -> {
                            try {
                                return postFromLine(line1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());
    }

    @Override
    public Post getById(Long id) throws IOException, ParseException {
        return getAll().stream().filter(line -> line.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Post save(Post newPost) throws IOException, ParseException {
        List<Post> list = getAll();
        newPost = new Post(generateID(list), newPost.getContent(), new Date(), new Date());
        list.add(newPost);
        writeLines(posts, list);
        return newPost;
    }

    @Override
    public Post update(Post post) throws IOException, ParseException {
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
    public void delete(Long id) throws IOException, ParseException {
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
                .map(Post::write)
                .collect(Collectors.toList());
        Files.write(Paths.get(path), newList);
    }

    private Post postFromLine(List<String> line) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
        return new Post(Long.parseLong(line.get(0)), line.get(1), df.parse(line.get(2)),
                df.parse(line.get(3)));
    }

    private Long generateID(List<Post> list) {
        if (!(list == null))
            return list.stream().map(Post::getId).max(Long::compare).get() + 1;
        else return null;
    }
}
