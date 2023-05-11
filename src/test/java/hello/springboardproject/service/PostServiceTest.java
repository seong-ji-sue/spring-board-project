package hello.springboardproject.service;

import hello.springboardproject.domain.Post;
import hello.springboardproject.repository.MemoryPostRepository;
import hello.springboardproject.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    PostService postService;
    MemoryPostRepository memoryPostRepository;



    @BeforeEach
    public void beforeEach() {
        memoryPostRepository = new MemoryPostRepository();
        postService = new PostService(memoryPostRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryPostRepository.clearStore();
    }

    @Test
    void create() {
        //given
        Post post = new Post("제목1", "내용1");

        //when
        Long saveId = postService.create(post);

        //then
        Post findPost = memoryPostRepository.findById(saveId).get();
        Assertions.assertEquals(post.getTitle(), findPost.getTitle());
    }

    @Test
    void findPosts() {
    }

    @Test
    void findOne() {
    }

    @Test
    void update() {
    }
}