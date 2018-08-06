package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.TabCategory;
import codesquad.repository.TabCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TabCategoryService {
    @Autowired
    TabCategoryRepository tabCategoryRepository;

    @Cacheable("Products")
    public List<TabCategory> findBestProducts() {
        return tabCategoryRepository.findAll();
    }

    //public
}
