package com.example.backend.repository;

import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    /**
     * username으로 사용자를 찾습니다.
     * @param username 찾을 사용자의 username
     * @return 찾은 User 객체, 없으면 null 반환
     */
    User findByUsername(@Param("username") String username);

    /**
     * 새로운 사용자 정보를 저장합니다.
     *
     * @param user 저장할 User 객체
     */
    void insertUser(User user);
}
