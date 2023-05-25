package com.ssafy.trip.mapper;


import com.ssafy.trip.models.User;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Optional<User> findByEmail(String email) throws SQLException;
    Optional<User> findById(long id) throws SQLException;

    int update(User user) throws SQLException;
    int updatePassword(Map map) throws SQLException;
    int delete(String id) throws SQLException;  //delete, deleteByID
    int insert(User user) throws SQLException; //insert

    int saveRefreshToken(Map map) throws SQLException;
    String getRefreshToken(long id) throws SQLException;



}