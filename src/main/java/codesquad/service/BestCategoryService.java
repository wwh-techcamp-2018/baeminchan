package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.domain.BestCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BestCategoryService {
    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    @Transactional
    @Cacheable(value="getBestCategoryListCache")
    public Iterable<BestCategory> getList() {
        return bestCategoryRepository.findAll();
    }
}
