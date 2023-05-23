package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.BlogDto;
import com.ssafy.trip.models.Blog;
import com.ssafy.trip.services.BlogService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("전체 공지")
public class BlogController {
    @Autowired
    BlogService blogService;


    @GetMapping("/posts")
    public ApiResult<List<BlogDto>> list(){
        try {
            return succeed(blogService.findAll().stream().map(BlogDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @GetMapping("/posts/{id}")
    public ApiResult<BlogDto> read(@PathVariable long id){
        try {
            return succeed(blogService.findById(id).map(BlogDto::new).orElseThrow(() -> new IllegalArgumentException("해당 id의 블로그 포스트가 없습니다.")));
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e);
        }

    }

    @DeleteMapping("/posts/{id}")
    public ApiResult<String> delete(@PathVariable long id){
        try {
            return succeed(blogService.delete(id));
        } catch (Exception e) {
            return failed(e);
        }

    }


    @PostMapping("/posts")
    public ApiResult<String> insert(@RequestBody Blog blog){
        try {
            return succeed(blogService.insert(blog));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @PutMapping("/posts")
    public ApiResult<String> update(@RequestBody Blog blog){
        try {
            return succeed(blogService.update(blog));
        } catch (Exception e) {
            return failed(e);
        }

    }
}
