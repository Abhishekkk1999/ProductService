package com.abhishek1st.ProductService.service;

import com.abhishek1st.ProductService.entity.Product;
import com.abhishek1st.ProductService.exception.ProductServiceCustomException;
import com.abhishek1st.ProductService.model.ProductRequest;
import com.abhishek1st.ProductService.model.ProductResponse;
import com.abhishek1st.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product..");

        Product product =
                Product.builder()
                        .productName(productRequest.getName())
                        .quantity(productRequest.getQuantity())
                        .price(productRequest.getPrice())
                        .build();

        productRepository.save(product);

        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product by productId: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }
}
