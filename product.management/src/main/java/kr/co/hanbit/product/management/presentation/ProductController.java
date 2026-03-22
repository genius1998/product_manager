package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import kr.co.hanbit.product.management.domain.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final SimpleProductService simpleProductService;

    public ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return simpleProductService.add(product);
    }
}