package com.example.userService.user.service;
import com.example.userService.user.dto.SelectUserDto;
import com.example.userService.user.dto.UserDto;
import com.example.userService.user.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public List<SelectUserDto> findUser() {
        return userMapper.getUser();
    }
    @Override
    public SelectUserDto findUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer insertUser(UserDto userDto) {
        return userMapper.insertUser(userDto);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public Integer updateUser(Integer id, UserDto userDto) {
        return userMapper.updateUser(id, userDto);
    }

    @Override
    public UserDto loginUser(UserDto userDto) {
        return userMapper.loginUser(userDto);
    }



}
