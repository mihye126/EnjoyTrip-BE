package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.NoticeDto;
import com.ssafy.trip.models.Notice;
import com.ssafy.trip.services.NoticeService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@Api("전체 공지")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/notices")
    public ApiResult<List<NoticeDto>> list(){
        try {
            return succeed(noticeService.findAll().stream().map(NoticeDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @GetMapping("/notices/{id}")
    public ApiResult<NoticeDto> read(@PathVariable long id){
        try {
            return succeed(noticeService.findById(id).map(NoticeDto::new).orElseThrow(() -> new IllegalArgumentException("해당 id의 공지가 없습니다.")));
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e);
        }

    }

    @DeleteMapping("/notices/{id}")
    public ApiResult<String> delete(@PathVariable long id){
        try {
            return succeed(noticeService.delete(id));
        } catch (Exception e) {
            return failed(e);
        }

    }


    @PostMapping("/notices")
    public ApiResult<String> insert(@RequestBody Notice notice){
        try {
            return succeed(noticeService.insert(notice));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @PutMapping("/notices")
    public ApiResult<String> update(@RequestBody Notice notice){
        try {
            return succeed(noticeService.update(notice));
        } catch (Exception e) {
            return failed(e);
        }

    }
}
