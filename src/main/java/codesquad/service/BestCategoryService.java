package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.repository.BestCategoryRepository;
import codesquad.web.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestCategoryService {
    public static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    @Cacheable("bestCategories")
    public List<BestCategory> findAll() {
        return bestCategoryRepository.findAll();
    }
}
