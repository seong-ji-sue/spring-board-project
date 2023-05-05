package hello.springboardproject.service;

import hello.springboardproject.domain.Post;
import hello.springboardproject.repository.MemoryPostRepository;
import hello.springboardproject.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository = new MemoryPostRepository();

    public Long create(Post post) {
        Post savePost = postRepository.save(post);
        return savePost.getId();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }

    public void update(Long postId, Post updatePost) {
        postRepository.update(postId,updatePost);
    }
}
