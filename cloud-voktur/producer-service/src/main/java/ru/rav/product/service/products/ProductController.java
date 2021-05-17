package ru.rav.product.service.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.rav.product.service.exceptions.ResourceNotFoundException;
import ru.rav.common.dto.ProductRequest;
import ru.rav.common.dto.ProductResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ResponseConverter responseConverter;

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        ProductEntity productEntity = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException(
                "Can't find product with that id"
        ));
        return responseConverter.toProductResponse(productEntity);
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PutMapping
    public ProductResponse updateProduct(@RequestBody @Valid ProductRequest updateRequest) {
        return productService.updateProduct(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
