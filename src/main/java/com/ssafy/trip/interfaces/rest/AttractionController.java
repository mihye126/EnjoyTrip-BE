package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.exception.member.NoSuchMemberException;
import com.ssafy.trip.interfaces.rest.dto.AttractionDto;
import com.ssafy.trip.models.Attraction;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Attraction(Trip)")
@CrossOrigin
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/trips/{contentID}")
    @ApiOperation(notes="여행지 1개의 정보를 담고 있는 페이지.", value="Trip Detail")
    public ApiResult<AttractionDto> read(
        @PathVariable String contentID
    ) {
        try {
            return succeed(
                attractionService.findByContentID(contentID)
                    .map(AttractionDto::new)
                    .orElseThrow(NoSuchMemberException::new)
            );
        } catch (Exception e) {
            return failed(e);
        }
    }

    @GetMapping("/trips")
    @ApiOperation(notes="여행지 전체 정보를 담고 있는 페이지.", value="Trip Detail")
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
    @PostMapping("/trips")
    @ApiOperation(notes="여행지 1개의 정보를 담고 있는 페이지.", value="Trip Detail")
    public ApiResult<String> insert(@RequestBody Attraction attraction) {

        try {
            attractionService.insert(attraction);
            return succeed("정상적으로 관광지 정보가 등록되었습니다. ");
        } catch (Exception e) {
            return failed(e);
        }
    }

    @PutMapping("/trips")
    @ApiOperation(notes="여행지 1개의 정보를 담고 있는 페이지.", value="Trip Detail")
    public ApiResult<String> update(@RequestBody Attraction attraction) {

        try {
            attractionService.update(attraction);
            return succeed("정상적으로 관광지 정보가 등록되었습니다. ");
        } catch (Exception e) {
            return failed(e);
        }
    }

    @GetMapping("/trips/search")
    @ApiOperation(notes="여행지 1개의 정보를 담고 있는 페이지.", value="Trip Detail")
    public ApiResult<String> search(
        @RequestParam(value = "sidoCode",required = true )String sidoCode,
        @RequestParam(value = "conentTypeId", required = false, defaultValue = "1") String conentTypeId,
        @RequestParam(value = "keyword", required = false)String keyword) {

        try {
            attractionService.search(sidoCode,conentTypeId,keyword);
            return succeed("정상적으로 관광지 정보가 등록되었습니다. ");
        } catch (Exception e) {
            return failed(e);
        }
    }

}
