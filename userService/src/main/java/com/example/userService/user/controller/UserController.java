package com.example.userService.user.controller;

import com.example.userService.config.security.SecurityService;
import com.example.userService.user.dto.SelectUserDto;
import com.example.userService.user.dto.UserDto;
import com.example.userService.user.service.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityService securityService;
//    @TokenRequired
//    @GetMapping("")
//    public List<SelectUserDto> getUser(){
//        return userService.findUser();
//    }
//
    @GetMapping("/{id}")
    public SelectUserDto getUser(@PathVariable Integer id){
        return userService.findUserById(id);
    }
//
//    @PostMapping("")
//    public Integer postUser(@RequestBody UserDto userDto){
//        return userService.insertUser(userDto);
//    }

//    @PutMapping("/{id}")
//    public Integer updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
//        return userService.updateUser(id, userDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public Integer deleteUser(@PathVariable Integer id){
//        return userService.deleteUser(id);
//    }

    @Operation(summary = "login", description = "로그인")
    @PostMapping("/login")
    public Map<String,Object> loginUser(@RequestBody UserDto userDto){
        try{
            UserDto user = userService.loginUser(userDto);
            String Id = user.getId().toString();
            // 여기에서 null 이 나올 가능성있음
            Map map = new HashMap<String,String>();
            map.put("token", securityService.createToken(Id));
            map.put("user",user);
            return map;
        }catch (NullPointerException e){
            throw new NullPointerException("not User");
        }
    }
    @Operation(summary = "join", description = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@RequestBody UserDto userDto){
        HttpStatus httpStatus= userService.insertUser(userDto)==-1
                ? HttpStatus.BAD_REQUEST:HttpStatus.CREATED ;

        return new ResponseEntity<>(httpStatus);
    }
    @Operation(summary = "check", description = "로그인체크")
    @GetMapping("/check")
    public boolean check(){
        return true;
    }

    @Operation(summary = "updateMyProfile", description = "내 프로필 수정")
    @PutMapping("")
    public Integer updateMyProfile(@RequestBody UserDto userDto){
        Integer myId = Integer.valueOf(securityService.getSubject(securityService.getToken()));
        return userService.updateUser(myId, userDto);
    }

}
