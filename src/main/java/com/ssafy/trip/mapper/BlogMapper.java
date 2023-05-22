package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Blog;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper
public interface BlogMapper {
    Optional<Blog> findById(long id) throws SQLException;
    List<Blog> findByAll() throws SQLException;
    int insert(Blog blog) throws  SQLException;
    int delete(long id) throws  SQLException;
    int update(Blog blog) throws  SQLException;
}
