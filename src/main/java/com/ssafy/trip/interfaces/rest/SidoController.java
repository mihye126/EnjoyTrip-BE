package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.SearchParameter;
import com.ssafy.trip.interfaces.rest.dto.SidoDto;
import com.ssafy.trip.services.SidoService;
import com.ssafy.trip.web.ApiResult;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("시도 정보 조회 API")
public class SidoController {

    @Autowired
    SidoService sidoService;

    @GetMapping("/sidos")
    public ApiResult<List<SidoDto>> list() {
        try {
            return succeed(
                sidoService.findAll().stream().map(SidoDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return failed(e);
        }

    }

    @GetMapping("/sidos/search")
    public ApiResult<SidoDto> search(
        @RequestParam(value = "param") String param,
        @RequestParam(value = "value") String value) {
        if (param.equals(SearchParameter.CODE.getParam())) {
            try {
                return succeed(sidoService.findByCode(value).map(SidoDto::new)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코드 입니다.")));
            } catch (Exception e) {
                return failed(e);
            }
        }

        if (param.equals(SearchParameter.NAME.getParam())) {
            try {
                return succeed(sidoService.findByName(value).map(SidoDto::new)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이름 입니다.")));
            } catch (Exception e) {
                return failed(e);
            }
        }

        return failed("존재하지 않는 파라미터입니다.");
    }


}
