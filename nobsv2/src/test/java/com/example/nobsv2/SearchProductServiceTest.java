package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.SearchProductService;
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

public class SearchProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private SearchProductService searchProductService;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void given_correct_name_when_search_product_service_then_return_product(){
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description which is at least 20 chars");
        product.setPrice(9.99);

        ProductDTO productDTO = new ProductDTO(product);
        List<ProductDTO> expectedDTOs = List.of(productDTO);

        when(productRepository.findByNameContaining("Product")).thenReturn(List.of(product));
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute("Product");
        assertEquals(ResponseEntity.ok().body(expectedDTOs), response);
        verify(productRepository, times(1)).findByNameContaining("Product");
    }
    @Test
    public void given_no_matching_name_when_search_product_service_then_return_empty_list() {
        when(productRepository.findByNameContaining("NonExistent")).thenReturn(List.of());
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute("NonExistent");
        assertEquals(ResponseEntity.ok().body(List.of()), response);
        verify(productRepository, times(1)).findByNameContaining("NonExistent");
    }

    @Test
    public void given_multiple_matching_products_when_search_product_service_then_return_all_matching_products() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product name");
        product1.setDescription("Product description which is at least 20 chars");
        product1.setPrice(9.99);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product second");
        product2.setDescription("Second product description");
        product2.setPrice(19.99);

        List<ProductDTO> productDTOS = List.of(new ProductDTO(product1), new ProductDTO(product2));
        when(productRepository.findByNameContaining("Product")).thenReturn(List.of(product1, product2));
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute("Product");
        assertEquals(ResponseEntity.ok().body(productDTOS), response);
        verify(productRepository, times(1)).findByNameContaining("Product");
    }

}
