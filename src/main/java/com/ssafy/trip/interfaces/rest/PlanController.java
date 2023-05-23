package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.PlanDto;
import com.ssafy.trip.models.Plan;
import com.ssafy.trip.services.PlanService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Trip Planner")
public class PlanController {

    @Autowired
    PlanService planService;

    @GetMapping("/plans")
    public ApiResult<List<PlanDto>> list(){
        try {
            return succeed(planService.findAll().stream().map(PlanDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @GetMapping("/plans/{id}")
    public ApiResult<PlanDto> read(@PathVariable long id){
        try {
            return succeed(planService.findById(id).map(PlanDto::new).orElseThrow(() -> new IllegalArgumentException("해당 id의 plan이 없습니다.")));
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e);
        }

    }

    @DeleteMapping ("/plans/{id}")
    public ApiResult<String> delete(@PathVariable long id){
        try {
            return succeed(planService.delete(id));
        } catch (Exception e) {
            return failed(e);
        }

    }


    @PostMapping("/plans")
    public ApiResult<String> insert(@RequestBody Plan plan){
        try {
            return succeed(planService.insert(plan));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @PutMapping("/plans")
    public ApiResult<String> update(@RequestBody Plan plan){
        try {
            return succeed(planService.update(plan));
        } catch (Exception e) {
            return failed(e);
        }

    }
}
