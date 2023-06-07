package com.abhishek1st.ProductService.service;

import com.abhishek1st.ProductService.model.ProductRequest;
import com.abhishek1st.ProductService.model.ProductResponse;

public interface ProductService {


    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
