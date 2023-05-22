package com.ssafy.trip.services;

import com.ssafy.trip.mapper.BlogMapper;
import com.ssafy.trip.models.Blog;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Optional<Blog> findById(long id) throws SQLException {
        return blogMapper.findById(id);
    }

    @Override
    public List<Blog> findByAll() throws SQLException {
        return blogMapper.findByAll();
    }

    @Override
    public int insert(Blog blog) throws SQLException {
        return blogMapper.insert(blog);
    }

    @Override
    public int delete(long id) throws SQLException {
        return blogMapper.delete(id);
    }

    @Override
    public int update(Blog blog) throws SQLException {
        return blogMapper.update(blog);
    }
}
