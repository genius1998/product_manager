package kr.co.hanbit.product.management.presentation;

import jakarta.validation.Valid;
import kr.co.hanbit.product.management.application.SimpleProductService;
import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final SimpleProductService simpleProductService;

    @Autowired
    public ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }



    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return simpleProductService.add(productDto);
    }


    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }


    @GetMapping
    public List<ProductDto> findProducts(
            @RequestParam(required = false) String name
    ) {
        if (name == null) {
            return simpleProductService.findAll();
        }

        return simpleProductService.findByNameContaining(name);
    }
    @PutMapping("/{id}")
    public ProductDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }
}