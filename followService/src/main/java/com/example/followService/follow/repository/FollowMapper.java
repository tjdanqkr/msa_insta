package com.example.followService.follow.repository;

import com.example.followService.follow.dto.FollowDto;
import com.example.followService.follow.dto.FollowUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    List<FollowUserDto> getFollowing(Integer followerId);
    List<FollowUserDto> getFollower(Integer followingId);
    List<FollowUserDto> getFollowerByOne(FollowDto followDto);
    Integer insertFollow(FollowDto followDto);
    Integer deleteFollow(FollowDto followDto);
}
