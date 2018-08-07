package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.dto.BestCategoryDto;
import codesquad.repository.BestCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BestCategoryServiceTest {
    @MockBean
    private BestCategoryRepository bestCategoryRepository;
    @SpyBean
    private BestCategoryService bestCategoryService;

    @Test
    public void findAllTestCaching() {
        BestCategory bestCategory1 = BestCategoryDto.defaultBestCategoryDto().setCategoryName("첫번째 카테고리").toEntity();
        when(bestCategoryRepository.findAll()).thenReturn(Arrays.asList(bestCategory1));
        bestCategoryService.findAll();
        bestCategoryService.findAll();
        bestCategoryService.findAll();
        verify(bestCategoryService, atMost(1)).findAll();
    }
}
