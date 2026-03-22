package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {

    private final ListProductRepository listProductRepository;

    public SimpleProductService(ListProductRepository listProductRepository) {
        this.listProductRepository = listProductRepository;
    }

    public Product add(Product product) {
        return listProductRepository.add(product);
    }
}