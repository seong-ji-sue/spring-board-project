package hello.springboardproject.domain.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PostRepositoryTest {

    PostRepository postRepository = new PostRepository();

    @AfterEach
    void afterEach() {
        postRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Post post = new Post("제목1", "내용1");

        //when
        Post savePost = postRepository.save(post);
        //then
        Post findPost = postRepository.findById(post.getId());
        Assertions.assertThat(savePost).isEqualTo(findPost);

    }

    @Test
    void findAll() {
        //given
        Post post1 = new Post("제목1", "내용1");
        Post post2 = new Post("제목2", "내용2");

        postRepository.save(post1);
        postRepository.save(post2);

        //when
        List<Post> result = postRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(post1,post2);
    }

    @Test
    void updatePost() {
        //given
        Post post = new Post("제목1", "내용1");
        Post savePost = postRepository.save(post);
        Long postId = savePost.getId();

        //when
        Post updateParam = new Post("제목2", "내용2");
        postRepository.update(postId,updateParam);

        //then
        Post findPost = postRepository.findById(postId);
        Assertions.assertThat(findPost.getTitle()).isEqualTo(updateParam.getTitle());
        Assertions.assertThat(findPost.getContent()).isEqualTo(updateParam.getContent());
    }
}