package hello.springboardproject.repository;

import hello.springboardproject.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
//    Optional<Post> findByTitle(String title);
    void update(Long id, Post updateParam);
}
