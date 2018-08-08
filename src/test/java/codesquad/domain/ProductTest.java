package codesquad.domain;

import codesquad.repository.MenuRepository;
import codesquad.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void eventProductTest(){
        menuRepository.findById(MenuContext.BEST_CATEGORY.getId())
                .get()
                .getChildren()
                .stream()
                .forEach(menu -> assertThat(productRepository.findAllByEventMenuId(menu.getId()).size()).isEqualTo(3));
    }
}
