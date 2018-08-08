package codesquad.web;

import codesquad.domain.Product;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/eventMenu/{menuId}/products")
    public List<Product> showProductsByEventMenuId(@PathVariable long menuId){
        return productService.showProducts(menuId);
    }
}
