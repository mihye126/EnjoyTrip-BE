package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.PlanDto;
import com.ssafy.trip.services.PlanService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Trip Planner")
public class PlanAPIs {

    @Autowired
    PlanService planService;

    @GetMapping("/plans")
    public ApiResult<List<PlanDto>> list(){
        try {
            return succeed(planService.findAll().stream().map(PlanDto::new).collect(Collectors.toList()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/plans/{id}")
    public ApiResult<PlanDto> read(@PathVariable long id){
        try {
            return succeed(planService.findById(id).map(PlanDto::new).orElseThrow(() -> new IllegalArgumentException("해당 id의 plan이 없습니다.")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/plans")
    public ApiResult<String> read(@PathVariable long id){
        try {
            return succeed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
