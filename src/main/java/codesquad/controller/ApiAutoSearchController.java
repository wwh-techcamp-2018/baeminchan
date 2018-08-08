package codesquad.controller;

import codesquad.domain.search.SearchItem;
import codesquad.service.SearchItemService;
import codesquad.util.CustomResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auto_search")
public class ApiAutoSearchController {
    @Autowired
    private SearchItemService searchItemService;

    @ApiOperation(value = "자동완성 조회", notes = "자동완성의 전체 데이터를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<SearchItem>> searchList() {
        return new CustomResponse<>(CustomResponse.MSG.OK, searchItemService.findAll());
    }
}
