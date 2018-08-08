package codesquad.controller;

import codesquad.domain.product.BestProduct;
import codesquad.service.BestProductService;
import codesquad.service.SearchItemService;
import codesquad.util.CustomResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ApiProductController {

    @Autowired
    private BestProductService bestProductService;

    @Autowired
    private SearchItemService searchItemService;

    @ApiOperation(value = "베스트상품 조회", notes = "베스트상품의 전체 데이터를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<BestProduct>> list() {
        return new CustomResponse<>(CustomResponse.MSG.OK, bestProductService.findAll());
    }
}
