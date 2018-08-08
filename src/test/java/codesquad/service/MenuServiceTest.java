package codesquad.service;


import codesquad.domain.Menu;
import codesquad.repository.MenuRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {

    private static final Logger log = LoggerFactory.getLogger(MenuServiceTest.class);
    private static final long ROOT_MENU = 1L;
    private static final long HAS_CHILD_MENU = 5L;
    private static final long HAS_NOT_CHILD_MENU = 14L;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRepository menuRepository;

    SoftAssertions softAssertions = new SoftAssertions();

    @Test
    @Transactional
    public void deleteTest_형제Index감소확인성공() {
        Menu deleteTarget = menuService.getSpecificMenu(HAS_NOT_CHILD_MENU);
        log.debug("deleteTarget : {}", menuService.getSpecificMenu(HAS_NOT_CHILD_MENU));
        menuService.deleteMenu(HAS_NOT_CHILD_MENU);
        Menu targetMenu = menuService.getSpecificMenu(HAS_NOT_CHILD_MENU + 1L);
        assertThat(deleteTarget.getIndex()).isEqualTo(targetMenu.getIndex());
    }

    @Test
    @Transactional
    public void deleteTest_부모childCount감소확인성공() {
        Menu deleteTarget = menuService.getSpecificMenu(HAS_NOT_CHILD_MENU);
        Long parentId = deleteTarget.getParent().getId();
        menuService.deleteMenu(HAS_NOT_CHILD_MENU);
        Menu parentAfterDelete = menuService.getSpecificMenu(parentId);
        log.debug("child list : {}", parentAfterDelete.getChildren().toString());
        log.debug("childCount값 : {}", parentAfterDelete.getChildCount());
        log.debug("실제 child 갯수 : {}", parentAfterDelete.getChildren().size());
        assertThat(parentAfterDelete.getChildCount()).isEqualTo(parentAfterDelete.getChildren().size());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void deleteTest_루트노드삭제() {
        log.debug("deleteTarget : {}", menuService.getSpecificMenu(ROOT_MENU));
        menuService.deleteMenu(ROOT_MENU);
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void deleteTest_자식있는노드삭제() {
        log.debug("deleteTarget : {}", menuService.getSpecificMenu(HAS_CHILD_MENU));
        menuService.deleteMenu(HAS_CHILD_MENU);
    }

    @Test
    @Transactional
    public void create_성공() {
        Menu beforeParent = menuService.getSpecificMenu(HAS_CHILD_MENU);
        int beforeChildrenSize = beforeParent.getChildren().size();
        int beforeChildCount = beforeParent.getChildCount();
        menuService.registMenu(beforeParent.getId(), "테스트데이터");
        Menu afterParent = menuService.getSpecificMenu(beforeParent.getId());
        softAssertions.assertThat(afterParent.getChildren().size()).isEqualTo(beforeChildrenSize + 1);
        softAssertions.assertThat(afterParent.getChildCount()).isEqualTo(beforeChildCount + 1);
        softAssertions.assertAll();
    }
}
