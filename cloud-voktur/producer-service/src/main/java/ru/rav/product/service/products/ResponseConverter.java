package ru.rav.product.service.products;

import org.springframework.stereotype.Component;
import ru.rav.common.dto.ProductResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseConverter {

    public ProductResponse toProductResponse(ProductEntity productEntity) {
        return new ProductResponse()
                .setId(productEntity.getId())
                .setTitle(productEntity.getTitle())
                .setPrice(productEntity.getPrice());
    }

    public List<ProductResponse> toProductResponse(List<ProductEntity> productEntities) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductEntity pr : productEntities) {
            productResponses.add(toProductResponse(pr));
        }
        return productResponses;
    }

}
