package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.AttractionDto;
import com.ssafy.trip.services.AttractionService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Attraction(Trip)")
@CrossOrigin
public class TripAPIs {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/trip/{contentID}")
    @ApiOperation(notes="여행지 1개의 정보를 담고 있는 페이지.", value="Trip Detail")
    public ApiResult<AttractionDto> read(
        @PathVariable String contentID
    ) {
        try {
            return succeed(
                attractionService.findByContentID(contentID)
                    .map(AttractionDto::new)
                    .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 Attraction입니다."))
            );
        } catch (Exception e) {
            return failed(e);
        }
    }

    @GetMapping("/trip")
    @ApiOperation(notes="여행지 1개의 정보를 담고 있는 페이지.", value="Trip Detail")
    public ApiResult<List<AttractionDto>> list() {
        try {
            return succeed(
                attractionService.findAll()
                    .stream()
                    .map(AttractionDto::new)
                    .collect(Collectors.toList())
            );
        } catch (Exception e) {
            return failed(e);
        }
    }


}
