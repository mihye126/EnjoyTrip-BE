package com.ssafy.trip.mapper;


import com.ssafy.trip.models.User;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Optional<User> selectOne(Map map) throws SQLException;
    Optional<User> findByEmail(String email) throws SQLException;

    int updateUser(User user) throws SQLException;
    int updatePassword(Map map) throws SQLException;
    int delete(User user) throws SQLException;  //delete, deleteByID
    int register(User user) throws SQLException; //insert
    Optional<User> check(Map map) throws SQLException;

    public void saveRefreshToken(Map<String, String> map) throws SQLException;
    public Object getRefreshToken(String userid) throws SQLException;
    public void deleteRefreshToken(Map<String, String> map) throws SQLException;
    public User userInfo(String userid) throws SQLException;



}