package com.ssafy.trip.services;

import com.ssafy.trip.mapper.ReviewMapper;
import com.ssafy.trip.models.MyBatisResult;
import com.ssafy.trip.models.Review;
import com.ssafy.trip.models.ReviewAvg;
import com.ssafy.trip.models.ReviewCount;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Optional<ReviewAvg> avgRatingGroupByContentId(long contentId) throws Exception {
        return reviewMapper.avgRatingGroupByContentId(contentId);
    }

    @Override
    public Optional<ReviewCount> countRatingGroupByContentId(long contentId) throws Exception {
        return reviewMapper.countRatingGroupByContentId(contentId);
    }

    @Override
    public String update(Review review) throws Exception {
        Map<String,Long> map=new HashMap<>();
        map.put("userId",review.getUserId());
        map.put("contentId", review.getContentId());
        int result=
            reviewMapper.findByUserIdAndContentId(map)==null?
                reviewMapper.insert(review): reviewMapper.update(review);

        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalAccessException("Review 등록에 실패하였습니다.");
        return "Review 등록에 성공하였습니다.";
    }
}
