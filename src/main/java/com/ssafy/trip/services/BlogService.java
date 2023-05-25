package com.ssafy.trip.services;

import com.ssafy.trip.models.Blog;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

public interface BlogService {
    Optional<Blog> findById(long id) throws Exception;
    List<Blog> findAll() throws Exception;
    String insert(Blog blog) throws  Exception;
    String delete(long id) throws  Exception;
    String update(Blog blog) throws  Exception;
}
