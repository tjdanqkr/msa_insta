package com.example.userService.config.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> illegalArgumentException(IllegalArgumentException e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg",e.getMessage());
        map.put("errCode","E00002");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<Map<String,Object>>  duplicateKeyException(DuplicateKeyException e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg","키 중복");
        map.put("errCode","E00001");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<Map<String,Object>>  jwtException(ExpiredJwtException e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg","토큰 에러");
        map.put("errCode","E00003");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String,Object>> Exception(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg",e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }
}
