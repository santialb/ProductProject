package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.DeleteProductService;
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

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_delete_product_service_then_delete_product(){
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setDescription("A valid description");
        product.setPrice(99.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        ResponseEntity<Void> response = deleteProductService.execute(1);
        assertEquals(ResponseEntity.noContent().build(), response);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_delete_product_service_then_throw_product_not_found_exception() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, never()).deleteById(anyInt());
    }

}
