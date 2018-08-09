package codesquad.service;

import codesquad.domain.Product;
import codesquad.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void search() {
        Product salmon1 = new Product("자연어처리");
        Product salmon2 = new Product("연어장");
        Product salmon3 = new Product("연어샐러드");

        when(productRepository.findByNameLike("%연어%")).thenReturn(Arrays.asList(salmon1, salmon2, salmon3));

        assertThat(productService.search("연어")).contains("자연어처리", "연어장");
    }
}
