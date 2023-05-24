package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Notice;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    Optional<Notice> findById(long id) throws SQLException;
    List<Notice> findAll(int pageNum) throws SQLException;
    int insert(Notice notice) throws  SQLException;
    int delete(long id) throws  SQLException;
    int update(Notice notice)  throws  SQLException;

    int count()  throws  SQLException;

}
