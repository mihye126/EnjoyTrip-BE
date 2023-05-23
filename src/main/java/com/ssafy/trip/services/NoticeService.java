package com.ssafy.trip.services;

import com.ssafy.trip.models.Notice;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    Optional<Notice> findById(long id) throws SQLException;
    List<Notice> findAll() throws SQLException;
    String insert(Notice notice) throws  SQLException;
    String delete(long id) throws  SQLException;
    String update(Notice notice)  throws  SQLException;
}
