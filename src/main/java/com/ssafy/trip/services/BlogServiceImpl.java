package com.ssafy.trip.services;

import com.ssafy.trip.mapper.BlogMapper;
import com.ssafy.trip.models.Blog;
import com.ssafy.trip.models.MyBatisResult;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Optional<Blog> findById(long id) throws Exception {
        return blogMapper.findById(id);
    }

    @Override
    public List<Blog> findByAll() throws Exception {
        return blogMapper.findByAll();
    }

    @Override
    public String insert(Blog blog) throws Exception {
        int result=blogMapper.insert(blog);
        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalArgumentException( "Blog 삽입에 실패하였습니다.");
        return "Blog 삽입에 성공하였습니다.";
    }

    @Override
    public String delete(long id) throws Exception {
        int result=blogMapper.delete(id);
        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalArgumentException( "해당되는 Blog가 존재하지 않아 Blog 삭제에 실패하였습니다.");
        return "Blog 삭제에 성공하였습니다.";     }

    @Override
    public String update(Blog blog) throws Exception {
        int result=blogMapper.update(blog);
        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalArgumentException( "해당되는 Blog가 존재하지 않아 Blog 수정에 실패하였습니다.");
        return "Blog 수정에 성공하였습니다.";
    }
}
