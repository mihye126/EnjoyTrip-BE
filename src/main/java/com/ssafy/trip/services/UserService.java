package com.ssafy.trip.services;


import com.ssafy.trip.models.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Optional<User> findByEmail(String email) throws Exception;
    Optional<User> findById(long id) throws Exception;

    String update(User user) throws Exception;
    String updatePassword(long id, String newpass) throws Exception;
    String delete(long id) throws Exception;
    String insert(User user) throws Exception;

    public String saveRefreshToken(long id, String refreshToken) throws Exception;
    public String getRefreshToken(long id) throws Exception;
}