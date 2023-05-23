package com.ssafy.trip.services;

import com.ssafy.trip.mapper.NoticeMapper;
import com.ssafy.trip.models.MyBatisResult;
import com.ssafy.trip.models.Notice;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Optional<Notice> findById(long id) throws SQLException {
        return noticeMapper.findById(id);
    }

    @Override
    public List<Notice> findAll() throws SQLException {
        return noticeMapper.findAll();
    }

    @Override
    public String insert(Notice notice) throws SQLException {
        int result=noticeMapper.insert(notice);
        if(result== MyBatisResult.FAIL.getResult())
            return  "공지 삽입에 실패하였습니다.";
        return "공지 삽입에 성공하였습니다.";
    }

    @Override
    public String delete(long id) throws SQLException {
        int result=noticeMapper.delete(id);
        if(result== MyBatisResult.FAIL.getResult())
            return  "공지 삭제 실패하였습니다.";
        return "공지 삭제 성공하였습니다.";
    }

    @Override
    public String update(Notice notice) throws SQLException {
        int result=noticeMapper.update(notice);
        if(result== MyBatisResult.FAIL.getResult())
            return  "공지 수정에 실패하였습니다.";
        return "공지 수정에 성공하였습니다.";
    }
}
