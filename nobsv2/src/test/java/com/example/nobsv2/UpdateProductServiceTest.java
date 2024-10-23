package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.UpdateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exist_when_update_product_service_then_update_product(){
        Product product = new Product();
        product.setId(1);
        product.setName("Old Name");
        product.setDescription("A valid description");
        product.setPrice(99.99);

        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Updated Name");
        updatedProduct.setDescription("Updated description");
        updatedProduct.setPrice(79.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        UpdateProductCommand updateProductCommand = new UpdateProductCommand(1, updatedProduct);
        ResponseEntity<ProductDTO> response = updateProductService.execute(updateProductCommand);
        assertEquals(ResponseEntity.ok().body(new ProductDTO(updatedProduct)), response);
        assertEquals("Updated Name", response.getBody().getName());
        assertEquals("Updated description", response.getBody().getDescription());
        verify(productRepository, times(1)).save(any(Product.class));
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_update_product_service_then_throw_product_not_found_exception(){
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> updateProductService.execute(new UpdateProductCommand(1, new Product())));
        verify(productRepository, times(1)).findById(1);
    }
}
