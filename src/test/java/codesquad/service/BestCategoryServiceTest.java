package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.repository.BestCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BestCategoryServiceTest {

    @Mock
    private BestCategoryRepository bestCategoryRepository;

    @InjectMocks
    private BestCategoryService bestCategoryService;

    private static final BestCategory seoulBestCategory = new BestCategory(1L, "서울맛집탐방");
    private static final BestCategory meatBestCategory = new BestCategory(2L, "풍성한 고기반찬");

    @Test
    public void findAll() {
        List<BestCategory> mockBestCategory = Arrays.asList(seoulBestCategory, meatBestCategory);
        when(bestCategoryRepository.findAll()).thenReturn(mockBestCategory);
    }

}
