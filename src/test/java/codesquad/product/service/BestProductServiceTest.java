package codesquad.product.service;

import codesquad.exception.ForbiddenException;
import codesquad.product.domain.BestProduct;
import codesquad.product.domain.BestProductRepository;
import codesquad.product.dto.BestProductDto;
import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BestProductServiceTest {
    @Mock
    private BestProductRepository bestProductRepository;

    @InjectMocks
    private BestProductService bestProductService;

    private User user;
    private BestProductDto dto;

    @Before
    public void setUp() throws Exception {
        user = User.builder().role(Role.ADMIN).build();
        dto = new BestProductDto("best product", null);
    }

    @Test
    public void delete() {
        BestProduct bestProduct = dto.toEntity();
        when(bestProductRepository.findById(bestProduct.getId())).thenReturn(Optional.of(bestProduct));

        assertThat(bestProductService.delete(user, bestProduct.getId())).isEqualTo(bestProduct);
    }

    @Test(expected = ForbiddenException.class)
    public void delete_not_admin() {
        user.setRole(Role.USER);
        BestProduct bestProduct = dto.toEntity();
        bestProductService.delete(user, bestProduct.getId());
    }
}
