package com.koreait.server.controller;

import com.koreait.server.model.Post;
import com.koreait.server.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> result = postService.getPostsWithPaging(page);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (post != null) {
            response.put("success", true);
            response.put("post", post);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "게시글을 찾을 수 없습니다.");
            return ResponseEntity.status(404).body(response);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody Post post, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(401).body(response);
        }
        
        post.setAuthorId(userId);
        postService.createPost(post);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글이 작성되었습니다.");
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody Post post, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(401).body(response);
        }
        
        post.setId(id);
        post.setAuthorId(userId);
        
        boolean updated = postService.updatePost(post);
        Map<String, Object> response = new HashMap<>();
        
        if (updated) {
            response.put("success", true);
            response.put("message", "게시글이 수정되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "게시글 수정 권한이 없습니다.");
            return ResponseEntity.status(403).body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(401).body(response);
        }
        
        boolean deleted = postService.deletePost(id, userId);
        Map<String, Object> response = new HashMap<>();
        
        if (deleted) {
            response.put("success", true);
            response.put("message", "게시글이 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "게시글 삭제 권한이 없습니다.");
            return ResponseEntity.status(403).body(response);
        }
    }
}
