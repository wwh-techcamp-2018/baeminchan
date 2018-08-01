package codesquad.domain;

import codesquad.repository.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuTest {

    private static final Logger log = LoggerFactory.getLogger(MenuTest.class);

    @Autowired
    MenuRepository menuRepository;

    private Menu main;

    private Menu child;

    @Before
    public void setUp() {
        Menu mainMenu = new Menu("전체메뉴");
        main = menuRepository.save(mainMenu);
        Menu menu1 = new Menu("국거리", main);
        child = menuRepository.save(menu1);
    }

    @Test
    public void basicTest() {
        Menu mainMenu = menuRepository.findById(main.getId()).get();
        log.debug("전체메뉴 : {}", mainMenu);
        assertThat(mainMenu.getName()).isEqualTo("전체메뉴");
    }

    @Test
    @Transactional
    public void parentTest() {
        Menu mainMenu = menuRepository.findById(main.getId()).get();
        log.debug("전체메뉴 : {}", mainMenu);
        log.debug("전체메뉴 자식 : {}", mainMenu.getChildren());
//        assertThat()
    }

    @Test
    @Transactional
    public void oneChildTest() {
        Menu mainMenu = menuRepository.findById(main.getId()).get();
        Menu menu = menuRepository.findById(child.getId()).get();
        log.debug("전체메뉴 : {} \n 자식메뉴 : {}", mainMenu, menu);
        assertThat(menu.getParent().getId()).isEqualTo(mainMenu.getId());
    }
}
