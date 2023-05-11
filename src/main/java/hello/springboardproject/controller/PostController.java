package hello.springboardproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springboardproject.domain.Post;
import hello.springboardproject.repository.MemoryPostRepository;
import hello.springboardproject.repository.PostRepository;
import hello.springboardproject.service.PostService;
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

    private final PostService postService;
    private ObjectMapper objectMapper = new ObjectMapper();

    //상품리스트
    @ResponseBody
    @GetMapping
    public List<Post> posts() {
        List<Post> posts = postService.findPosts();
        return posts;
    }

    //상품 상세
    @ResponseBody
    @GetMapping("/{postId}")
    public Post post(@PathVariable Long postId) {
        Post post = postService.findOne(postId).get();
        return post;
    }

    //상품 등록
    @ResponseBody
    @PostMapping("/add")
    public Long addPost(@RequestBody Post post) {
        Long postId = postService.create(post);
        return postId;
    }

    //상품 수정
    @ResponseBody
    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @RequestBody Post post) {
        postService.update(postId, post);
        return "ok";
    }



}