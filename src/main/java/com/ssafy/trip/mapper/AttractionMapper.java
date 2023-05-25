package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Attraction;
import com.ssafy.trip.models.Count;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionMapper {
    Optional<Attraction> findByContentID(String contentID) throws SQLException;
    List<Attraction> findBySidoCode(String SidoCode) throws SQLException;
    List<Attraction> findByContentTypeId(String contentTypeId) throws SQLException;
    List<Attraction> findBySidoCodeAndContentTypeId(Map map) throws SQLException;
    Optional<Count> countFindBySidoCodeAndContentTypeId(Map map) throws SQLException;
    List<Attraction> findByOverviewContainingOrTitleContaining(String keyword) throws SQLException;
    List<Attraction> findByOverviewContainingOrTitleContainingAndSidoCodeAndContentTypeId(Map map) throws SQLException;
    Optional<Count> countFindByOverviewContainingOrTitleContainingAndSidoCodeAndContentTypeId(Map map) throws SQLException;
    List<Attraction> findByOverviewContainingOrTitleContainingAndSidoCode(Map map) throws SQLException;
    List<Attraction> findByOverviewContainingOrTitleContainingAndContentTypeId(Map map) throws SQLException;

    List<Attraction> findAll() throws SQLException;
    int insert(Attraction attraction) throws SQLException;
    int delete(String contentID) throws SQLException;
    int update(Attraction attraction) throws SQLException;

}
