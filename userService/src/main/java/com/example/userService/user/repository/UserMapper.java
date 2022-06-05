package com.example.userService.user.repository;

import com.example.userService.user.dto.SelectUserDto;
import com.example.userService.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<SelectUserDto> getUser();
    SelectUserDto getUserById(Integer id);
    Integer insertUser(UserDto UserDto);

    Integer updateUser(Integer id, UserDto UserDto );
    Integer deleteUser(Integer id);

    UserDto loginUser(UserDto UserDto);

}
