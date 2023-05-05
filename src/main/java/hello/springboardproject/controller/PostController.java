package hello.springboardproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springboardproject.domain.Post;
import hello.springboardproject.repository.MemoryPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final MemoryPostRepository postRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    //상품리스트
    @ResponseBody
    @GetMapping
    public List<Post> posts(Model model) {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    //상품 상세
    @ResponseBody
    @GetMapping("/{postId}")
    public Post post(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    //상품 등록
    @ResponseBody
    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        Post savePost = postRepository.save(post);
        return savePost;
    }

    //상품 수정
    @ResponseBody
    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @RequestBody Post post) {
        postRepository.update(postId, post);
        return "ok";
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