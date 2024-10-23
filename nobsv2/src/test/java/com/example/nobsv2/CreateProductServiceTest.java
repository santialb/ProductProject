package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void given_valid_product_when_create_product_service_then_product_is_created(){
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setDescription("A valid description");
        product.setPrice(99.99);

        Product savedProduct = new Product();
        savedProduct.setId(1);
        savedProduct.setName("Product name");
        savedProduct.setDescription("A valid description");
        savedProduct.setPrice(99.99);

        when(productRepository.save(product)).thenReturn(savedProduct);
        ResponseEntity<ProductDTO> response = createProductService.execute(product);
        assertEquals(ResponseEntity.status(201).body(new ProductDTO(savedProduct)), response);
        verify(productRepository, times(1)).save(product);
    }


}
