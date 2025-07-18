package com.example.board.service;

import com.example.board.dto.Post;
import com.example.board.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class PostService {
    
    @Autowired
    private PostMapper postMapper;
    
    public Map<String, Object> getPostsWithPaging(int page) {
        int limit = 10;
        int offset = (page - 1) * limit;
        
        List<Post> posts = postMapper.findAllWithPaging(limit, offset);
        int totalCount = postMapper.countAll();
        int totalPages = (int) Math.ceil((double) totalCount / limit);
        
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        result.put("currentPage", page);
        result.put("totalPages", totalPages);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    public Post getPostById(Long id) {
        return postMapper.findById(id);
    }
    
    public void createPost(Post post) {
        postMapper.insertPost(post);
    }
    
    public boolean updatePost(Post post) {
        return postMapper.updatePost(post) > 0;
    }
    
    public boolean deletePost(Long id, Long authorId) {
        return postMapper.deletePost(id, authorId) > 0;
    }
}
