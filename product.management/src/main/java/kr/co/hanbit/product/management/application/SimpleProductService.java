package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.dto.ProductDto;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SimpleProductService(ListProductRepository listProductRepository,
                                ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto add(ProductDto productDto) {

        // 1. ProductDto → Product 변환
        Product product = modelMapper.map(productDto, Product.class);

        // 2. 레포지토리 호출
        Product savedProduct = listProductRepository.add(product);

        // 3. Product → ProductDto 변환
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        // 4. DTO 반환
        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        Product product = listProductRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        Product updatedProduct = listProductRepository.update(product);

        ProductDto updatedProductDto =
                modelMapper.map(updatedProduct, ProductDto.class);

        return updatedProductDto;
    }

    public void delete(Long id) {
        listProductRepository.delete(id);
    }
}