package hello.springboardproject.domain.post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private static final Map<Long, Post> store = new HashMap<>();//ConcurrentHashMap
    private static long sequence = 0L;

    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());//감의 변경을 방지하기 위함
    }

    public void update(Long postId, Post updateParam) {
        Post findPost = findById(postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }
    //테스트 용도
    public void clearStore() {
        store.clear();
    }
}