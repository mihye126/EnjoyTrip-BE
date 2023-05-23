package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Review;
import com.ssafy.trip.models.ReviewAvg;
import com.ssafy.trip.models.ReviewCount;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    // Attraction id집어 넣어서 Review 평균가져오기
    // 별점 삽입
    // 별점 수정
    // 별점 가져오기

    Optional<ReviewAvg> avgRatingGroupByContentId(long contentId);
    Optional<ReviewCount> countRatingGroupByContentId(long contentId);
    int insert(Review review);
    int update(Review review);
    Review findByUserIdAndContentId(Map map);
}
