package com.ssafy.trip.services;

import com.ssafy.trip.models.Notice;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    Optional<Notice> findById(long id) throws Exception;
    List<Notice> findAll() throws Exception;
    String insert(Notice notice) throws  Exception;
    String delete(long id) throws  Exception;
    String update(Notice notice)  throws  Exception;
}
