package com.example.followService.follow.service;

import com.example.followService.follow.dto.FollowDto;
import com.example.followService.follow.dto.FollowUserDto;

import java.util.List;

public interface FollowService {

    List<FollowUserDto> getFollowing(Integer followerId);
    List<FollowUserDto> getFollower(Integer followingId);

    List<FollowUserDto> getFollowerByOne(FollowDto followDto);
    Integer insertFollow(FollowDto followDto);
    Integer deleteFollow(FollowDto followDto);
}
