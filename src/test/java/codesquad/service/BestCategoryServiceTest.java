package codesquad.service;

import codesquad.dto.BestCategoryDto;
import codesquad.repository.BestCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BestCategoryServiceTest {
    @Mock
    private BestCategoryRepository bestCategoryRepository;
    @InjectMocks
    private BestCategoryService bestCategoryService;
    @Test
    public void findAllTest() {
        when(bestCategoryRepository.findAll()).thenReturn(Arrays.asList(BestCategoryDto.defaultBestCategoryDto().toEntity()));
        assertThat(bestCategoryService.findAll()).isEqualTo(Arrays.asList(BestCategoryDto.defaultBestCategoryDto()));
    }
}
