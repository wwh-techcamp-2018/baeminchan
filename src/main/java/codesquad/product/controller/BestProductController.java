package codesquad.product.controller;

import codesquad.product.domain.BestProduct;
import codesquad.product.dto.BestProductDto;
import codesquad.product.service.BestProductService;
import codesquad.security.LoginUser;
import codesquad.support.dto.response.ResponseModel;
import codesquad.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bestProducts")
public class BestProductController {
    @Autowired
    private BestProductService bestProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseModel<BestProduct> create(@LoginUser(adminRequired = true) User admin, @Valid @RequestBody BestProductDto dto) {
        return ResponseModel.ofSuccess(bestProductService.add(dto.toEntity()), null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<List<BestProduct>> show() {
        return ResponseModel.ofSuccess(bestProductService.show(), null);
    }

    @DeleteMapping("/{bestProductId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<BestProduct> delete(@LoginUser(adminRequired = true) User admin, @PathVariable Long bestProductId) {
        return ResponseModel.ofSuccess(bestProductService.delete(admin, bestProductId), null);
    }
}
