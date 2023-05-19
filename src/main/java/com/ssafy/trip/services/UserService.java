package com.ssafy.trip.services;


import com.ssafy.trip.models.User;
import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    Optional<User> selectOne(String id, String pw) throws Exception;
    Optional<User> findByEmail(String email) throws Exception;


    int updateUser(User user) throws Exception;
    int updatePassword(String id, String newpass) throws Exception;

    int delete(User user) throws Exception;

    int register(User user) throws Exception;

    Optional<User> check(String id, String pw) throws Exception;

    public void saveRefreshToken(String userid, String refreshToken) throws Exception;
    public Object getRefreshToken(String userid) throws Exception;
    public void deleRefreshToken(String userid) throws Exception;
    public User userInfo(String userid) throws Exception;
}