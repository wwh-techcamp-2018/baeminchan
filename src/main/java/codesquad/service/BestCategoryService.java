package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.repository.BestCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestCategoryService {
    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    @Cacheable(value="findByBestCategoryCache")
    public List<BestCategory> findAll() {
        return bestCategoryRepository.findAll().stream().collect(Collectors.toList());
    }

}
