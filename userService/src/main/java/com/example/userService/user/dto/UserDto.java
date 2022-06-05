package com.example.userService.user.dto;

import lombok.Data;


@Data
public class UserDto {
    private Integer id;
    private String userId;
    private String password;
    private String img;
    private String name;

}
