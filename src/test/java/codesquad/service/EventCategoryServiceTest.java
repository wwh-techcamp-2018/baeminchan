package codesquad.service;

import codesquad.domain.EventCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EventCategoryServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EventCategoryServiceTest.class);
    
    @Autowired
    EventCategoryService eventCategoryService;

    private long startTime;
    private long endTime;

    @Before
    public void setUp() throws Exception {
        startTime = System.currentTimeMillis();
    }

    @After
    public void tearDown() throws Exception {
        endTime = System.currentTimeMillis();
        log.debug("소요시간 : {}ms", endTime - startTime);
    }

    @Test
    public void firstDataSelect() {
        eventCategoryService.readEventCategories();
    }

    @Test
    public void cacheDataSelect() {
        eventCategoryService.readEventCategories();
    }
}