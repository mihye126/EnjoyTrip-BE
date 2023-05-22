package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Notice;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper
public interface NoticeMapper {
    Optional<Notice> findById(long id) throws SQLException;
    List<Notice> findByAll() throws SQLException;
    int insert(Notice notice) throws  SQLException;
    int delete(long id) throws  SQLException;
    int update(Notice notice)  throws  SQLException;

}
