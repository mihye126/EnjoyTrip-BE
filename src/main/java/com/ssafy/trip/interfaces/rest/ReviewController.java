package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.ReviewAvgDto;
import com.ssafy.trip.interfaces.rest.dto.ReviewCountDto;
import com.ssafy.trip.models.Review;
import com.ssafy.trip.services.ReviewService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Attraction 별점 API")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews/avg/{contentId}")
    public ApiResult<ReviewAvgDto> avg(@PathVariable long contentId){
        try {
            return succeed(reviewService.avgRatingGroupByContentId(contentId).map(ReviewAvgDto::new).orElse(new ReviewAvgDto(contentId,0)));
        } catch (Exception e) {
            return failed(e);
        }

    }


    @GetMapping("/reviews/count/{contentId}")
    public ApiResult<ReviewCountDto> count(@PathVariable long contentId){
        try {
            return succeed(reviewService.countRatingGroupByContentId(contentId).map(ReviewCountDto::new).orElse(new ReviewCountDto(contentId,0)));
        } catch (Exception e) {
            return failed(e);
        }

    }


    @PostMapping("/reviews/update")
    public ApiResult<String> insert(@RequestBody Review review){
        try {
            return succeed(reviewService.update(review));
        } catch (Exception e) {
            return failed(e);
        }

    }

}
