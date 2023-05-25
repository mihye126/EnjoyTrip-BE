package com.ssafy.trip.services;


import com.ssafy.trip.mapper.UserMapper;
import com.ssafy.trip.models.MyBatisResult;
import com.ssafy.trip.models.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) throws Exception {
        return userMapper.findByEmail(email);
    }

    @Override
    public Optional<User> findById(long id) throws Exception {
        return userMapper.findById(id);
    }

    @Override
    public String update(User user) throws Exception{
        int result=userMapper.update(user);
        if(result== MyBatisResult.FAIL.getResult())
            throw  new IllegalAccessException("유저 업데이트에 실패하였습니다.");
        return "유저 업데이트에 성공하였습니다.";
    }

    @Override
    public String updatePassword(long id, String newpass) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("pw", newpass);
        int result=userMapper.updatePassword(map);
        if(result== MyBatisResult.FAIL.getResult())
            throw  new IllegalAccessException( "비밀번호 업데이트에 실패하였습니다.");
        return "비밀번호 업데이트에 성공하였습니다.";
    }

    @Override
    public String delete(long id) throws Exception {
        int result=userMapper.delete(id);
        if(result== MyBatisResult.FAIL.getResult())
            throw  new IllegalAccessException( "유저 삭제에 실패하였습니다.");
        return "유저 삭제에 성공하였습니다.";
    }

    @Override
    public String insert(User user) throws Exception {
        int result=userMapper.insert(user);
        if(result== MyBatisResult.FAIL.getResult())
            throw  new IllegalAccessException( "유저 등록에 실패하였습니다.");
        return "유저 등록에 성공하였습니다.";
    }

    @Override
    public String saveRefreshToken(long id, String refreshToken) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", refreshToken);
        int result=userMapper.saveRefreshToken(map);
        return null;
    }

    @Override
    public String getRefreshToken(long id) throws Exception {
        return userMapper.getRefreshToken(id);
    }



}
