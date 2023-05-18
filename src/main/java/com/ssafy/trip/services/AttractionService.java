package com.ssafy.trip.services;

import com.ssafy.trip.models.Attraction;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface AttractionService {

    Optional<Attraction> findByContentID(String contentID) throws SQLException;
    List<Attraction> findAll() throws SQLException;
    void insert(Attraction attraction) throws SQLException;

//    List<Attraction> selectAll() throws SQLException;




}
