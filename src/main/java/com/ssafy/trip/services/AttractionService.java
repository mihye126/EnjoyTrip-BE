package com.ssafy.trip.services;

import com.ssafy.trip.models.Attraction;
import com.ssafy.trip.models.Count;
import io.swagger.models.auth.In;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface AttractionService {

    Optional<Attraction> findByContentID(String contentID) throws Exception;
    List<Attraction> findAll() throws Exception;
    String insert(Attraction attraction) throws Exception;
    String delete(String contentID) throws Exception;
    String update(Attraction attraction) throws Exception;

    List<Attraction>  search(String sidoCode, String contentTypeId, String keyword,int pageNum)  throws Exception;
    Optional<Count> count(String sidoCode, String contentTypeId, String keyword)  throws Exception;




}
