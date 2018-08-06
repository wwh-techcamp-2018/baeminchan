package codesquad.service;

import codesquad.dto.BestCategoryDto;
import codesquad.repository.BestCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestCategoryService {
    @Autowired
    private BestCategoryRepository bestCategoryRepository;
    @Autowired
    private CacheManager ehCacheCacheManager;
    
    @Cacheable(value="findByBestCategoryCache")
    public List<BestCategoryDto> findAll() {
        return bestCategoryRepository.findAll().stream().map(bc -> BestCategoryDto.fromEntity(bc)).collect(Collectors.toList());
    }

}
