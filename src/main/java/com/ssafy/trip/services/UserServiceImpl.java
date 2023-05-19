package com.ssafy.trip.services;


import com.ssafy.trip.mapper.UserMapper;
import com.ssafy.trip.models.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<User> selectOne(String id, String pw) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pw", pw);
        return userMapper.selectOne(map);
    }

    @Override
    public Optional<User> findByEmail(String email) throws Exception {
        return userMapper.findByEmail(email);
    }

    @Override
    public int updateUser(User user) throws Exception{
        return userMapper.updateUser(user);
    }

    @Override
    public int updatePassword(String id, String newpass) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pw", newpass);
        return userMapper.updatePassword(map);
    }

    @Override
    public int delete(User user) throws Exception {
        return userMapper.delete(user);
    }

    @Override
    public int register(User user) throws Exception {
        return userMapper.register(user);
    }

    @Override
    public Optional<User> check(String id, String pw) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pw", pw);
        return userMapper.check(map);
    }


    @Override
    public void saveRefreshToken(String userid, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", userid);
        map.put("token", refreshToken);
        userMapper.saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userid) throws Exception {
        return userMapper.getRefreshToken(userid);
    }

    @Override
    public void deleRefreshToken(String userid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", null);
        userMapper.deleteRefreshToken(map);
    }

    @Override
    public User userInfo(String userid) throws Exception {
        return userMapper.userInfo(userid);
    }
}
