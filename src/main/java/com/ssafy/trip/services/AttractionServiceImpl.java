package com.ssafy.trip.services;


import com.ssafy.trip.exception.member.NoSuchMemberException;
import com.ssafy.trip.mapper.AttractionMapper;
import com.ssafy.trip.models.Attraction;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttractionServiceImpl  implements AttractionService{ //service에서 exception 처리한다.

    @Autowired
    private AttractionMapper attractionMapper;

    @Override
    public Optional<Attraction> findByContentID(String contentID) throws SQLException {
        return attractionMapper.findByContentID(contentID);
    }

    @Override
    public List<Attraction> findAll() throws SQLException {
        return attractionMapper.findAll();
    }

    @Override
    public int insert(Attraction attraction) throws SQLException {
        // 삽입하기 -> 유저가 추가한 관광지
        return attractionMapper.insert(attraction);
    }

    public int delete(String contentID) throws SQLException {
        // 삭제하기 -> 유저가 추가한 관광지만 삭제 가능하도록
        return attractionMapper.delete(contentID);
    }
}
