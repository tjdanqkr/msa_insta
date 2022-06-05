package com.example.postService.post.dto;

import lombok.Data;

@Data
public class PostUserDto {
    private Integer id;
    private Integer userId;
    private String img;
    private String content;
    private String userImg;
    private String userName;
    private boolean isFollow;
}
