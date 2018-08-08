package codesquad.service;

import codesquad.domain.category.EventCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EventCategoryServiceTest {

    @Autowired
    private EventCategoryService eventCategoryService;

    private EventCategoryRepository mockCategoryRespository;

    @Before
    public void setUp() throws Exception {
        mockCategoryRespository = Mockito.mock(EventCategoryRepository.class);
        eventCategoryService.setEventCategoryRepository(mockCategoryRespository);
    }

    @Test
    public void cacheTest() {
        when(mockCategoryRespository.findAll()).thenReturn(null);
        eventCategoryService.readEventCategories();
        eventCategoryService.readEventCategories();
        verify(mockCategoryRespository, times(1)).findAll();
    }

}