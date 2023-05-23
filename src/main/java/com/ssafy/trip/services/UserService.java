package com.ssafy.trip.services;


import com.ssafy.trip.models.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Optional<User> findByEmail(String email) throws Exception;
    String update(User user) throws Exception;
    String updatePassword(String id, String newpass) throws Exception;
    String delete(User user) throws Exception;
    String insert(User user) throws Exception;

    public void saveRefreshToken(String userid, String refreshToken) throws Exception;
    public Object getRefreshToken(String userid) throws Exception;
    public void deleRefreshToken(String userid) throws Exception;
    public User userInfo(String userid) throws Exception;
}