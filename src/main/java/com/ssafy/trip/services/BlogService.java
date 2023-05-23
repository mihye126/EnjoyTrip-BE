package com.ssafy.trip.services;

import com.ssafy.trip.models.Blog;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    Optional<Blog> findById(long id) throws SQLException;
    List<Blog> findByAll() throws SQLException;
    String insert(Blog blog) throws  SQLException;
    String delete(long id) throws  SQLException;
    String update(Blog blog) throws  SQLException;
}
