package com.example.board.mapper;

import com.example.board.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findAllWithPaging(@Param("limit") int limit, @Param("offset") int offset);
    int countAll();
    Post findById(Long id);
    void insertPost(Post post);
    int updatePost(Post post);
    int deletePost(@Param("id") Long id, @Param("authorId") Long authorId);
}
