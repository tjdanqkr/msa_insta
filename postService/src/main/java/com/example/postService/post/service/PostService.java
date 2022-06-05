package com.example.postService.post.service;

import com.example.postService.post.dto.PostDto;
import com.example.postService.post.dto.PostUserDto;

import java.util.List;

public interface PostService {
    List<PostUserDto> getAllPost();
    List<PostUserDto> getMyPost(Integer userId);
    List<PostUserDto> getOtherPost(Integer userId);
    List<PostUserDto> getPostByUserId(Integer userId);
    PostUserDto getPostById(Integer postId);
    Integer postPost(PostDto postDto);
    Integer updatePost(PostDto postDto);
    Integer deletePost(PostDto postDto);
    List<PostUserDto> getPostByMyFollowing(Integer userId);

    List<PostUserDto> getPostByKey(String key);
}
