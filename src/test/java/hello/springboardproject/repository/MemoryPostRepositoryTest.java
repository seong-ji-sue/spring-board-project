package hello.springboardproject.repository;

import hello.springboardproject.domain.Post;
import hello.springboardproject.repository.MemoryPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

public class MemoryPostRepositoryTest {

    MemoryPostRepository memoryPostRepository = new MemoryPostRepository();

    @AfterEach
    void afterEach() {
        memoryPostRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Post post = new Post("제목1", "내용1");

        //when
        Post savePost = memoryPostRepository.save(post);
        //then
        Post findPost = memoryPostRepository.findById(post.getId()).get();
        Assertions.assertThat(savePost).isEqualTo(findPost);

    }


    @Test
    void findAll() {
        //given
        Post post1 = new Post("제목1", "내용1");
        Post post2 = new Post("제목2", "내용2");

        memoryPostRepository.save(post1);
        memoryPostRepository.save(post2);

        //when
        List<Post> result = memoryPostRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(post1,post2);
    }

    @Test
    void updatePost() {
        //given
        Post post = new Post("제목1", "내용1");
        Post savePost = memoryPostRepository.save(post);
        Long postId = savePost.getId();

        //when
        Post updateParam = new Post("제목2", "내용2");
        memoryPostRepository.update(postId,updateParam);

        //then
        Post findPost = memoryPostRepository.findById(postId).get();
        Assertions.assertThat(findPost.getTitle()).isEqualTo(updateParam.getTitle());
        Assertions.assertThat(findPost.getContent()).isEqualTo(updateParam.getContent());
    }
}