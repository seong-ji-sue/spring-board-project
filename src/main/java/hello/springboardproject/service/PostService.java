package hello.springboardproject.service;

import hello.springboardproject.domain.Post;
import hello.springboardproject.repository.MemoryPostRepository;
import hello.springboardproject.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository = new MemoryPostRepository();

    public PostService(PostRepository postRepository) {
    }

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
    @PostConstruct
    public void init() {
        postRepository.save(new Post("제목1","내용1"));
        postRepository.save(new Post("제목2","내용2"));
        postRepository.save(new Post("제목3","내용3"));
        postRepository.save(new Post("제목4","내용4"));
        postRepository.save(new Post("제목5","내용5"));
    }
}
