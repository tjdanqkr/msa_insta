package com.example.postService.post.repository;

import com.example.postService.post.dto.PostDto;
import com.example.postService.post.dto.PostUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostMapper {
    List<PostUserDto> getPostAll();
    PostUserDto getPostById(Integer postId);
    List<PostUserDto> getPostByUserId(Integer userId);
    List<PostUserDto> getPostByNotUserId(Integer userId);
    List<PostUserDto> getPostByMyFollowing(Integer userId);
    Integer insertPost(PostDto postDto);
    Integer updatePost(PostDto postDto);
    Integer deletePost(PostDto postDto);
    List<PostUserDto> getPostByKey(String key);
}
