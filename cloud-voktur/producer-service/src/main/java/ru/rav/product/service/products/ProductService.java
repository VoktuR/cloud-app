package ru.rav.product.service.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.rav.common.dto.ProductRequest;
import ru.rav.common.dto.ProductResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ResponseConverter responseConverter;

    public Optional<ProductEntity> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductResponse> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return responseConverter.toProductResponse(productEntities);
    }

    public ProductResponse saveProduct(ProductRequest saveRequest) {
        saveRequest.setId(null);
        return responseConverter.toProductResponse(productRepository.save(new ProductEntity(saveRequest)));
    }

    public ProductResponse updateProduct(ProductRequest updateRequest) {
        return responseConverter.toProductResponse(productRepository.save(new ProductEntity(updateRequest)));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
