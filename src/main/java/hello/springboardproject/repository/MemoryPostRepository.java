package hello.springboardproject.repository;

import hello.springboardproject.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryPostRepository implements PostRepository {
    private static final Map<Long, Post> store = new HashMap<>();//ConcurrentHashMap
    private static long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return store.get(post.getId());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
//    //게시물 검색 구현 예정
//    @Override
//    public Optional<Post> findByTitle(String title) {
//        return store.values().stream()
//                .filter(post -> post.getTitle().equals(title))
//                .findAny();
//    }
    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());//감의 변경을 방지하기 위함
    }


    @Override
    public void update(Long postId, Post updateParam) {
        Post findPost = findById(postId).get();
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }
    //테스트 용도
    public void clearStore() {
        store.clear();
    }
}