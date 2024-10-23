package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_products_exist_when_get_products_service_then_return_product_dtos() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product 1");
        product1.setDescription("Description for product 1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setDescription("Description for product 2");
        product2.setPrice(20.0);

        List<Product> products = List.of(product1, product2);
        when(productRepository.findAll()).thenReturn(products);
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);
        List<ProductDTO> expectedDTOs = products.stream().map(ProductDTO::new).toList();
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(expectedDTOs), response);
        verify(productRepository, times(1)).findAll();
    }
}
