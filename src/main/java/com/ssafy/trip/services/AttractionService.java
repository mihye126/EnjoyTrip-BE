package com.ssafy.trip.services;

import com.ssafy.trip.models.Attraction;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface AttractionService {

    Optional<Attraction> findByContentID(String contentID) throws Exception;
//    List<Attraction> findBySidoCode(String SidoCode) throws SQLException;
//    List<Attraction> findByContentTypeId(String contentTypeId) throws SQLException;
//    List<Attraction> findBySidoCodeAndContentTypeId(String SidoCode,String contentTypeId) throws SQLException;
//    List<Attraction> findByOverviewContainingOrTitleContaining(String SidoCode,String contentTypeId) throws SQLException;
    List<Attraction> findAll() throws Exception;
    String insert(Attraction attraction) throws Exception;
    String delete(String contentID) throws Exception;
    String update(Attraction attraction) throws Exception;

    List<Attraction>  search(String sidoCode, String conentTypeId, String keyword)  throws Exception;
//    List<Attraction> selectAll() throws SQLException;




}
