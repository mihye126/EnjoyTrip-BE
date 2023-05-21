package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Attraction;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionMapper {
    Optional<Attraction> findByContentID(String contentID) throws SQLException;
    List<Attraction> findAll() throws SQLException;
    int insert(Attraction attraction) throws SQLException;
    int delete(String contentID) throws SQLException;


}
