package com.ssafy.trip.services;

import com.ssafy.trip.models.Review;
import com.ssafy.trip.models.ReviewAvg;
import com.ssafy.trip.models.ReviewCount;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Optional<ReviewAvg> avgRatingGroupByContentId(long contentId) throws Exception;
    Optional<ReviewCount> countRatingGroupByContentId(long contentId) throws Exception;
    String update(Review review)throws Exception;
//    int insert(;
//    int update(Review review);
//    Optional<Review> findByUserIdAndContentId(Map map) throws Exception;

}
