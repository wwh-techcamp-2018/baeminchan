package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.domain.BestCategoryRepository;
import codesquad.domain.SideDish;
import codesquad.exception.NotMatchedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestCategoryService {
    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    public Iterable<BestCategory> getAll() {
        return bestCategoryRepository.findAll();
    }

    public Iterable<SideDish> getBestSideDishes(Long id) {
        return bestCategoryRepository.findById(id)
                .orElseThrow(() -> new NotMatchedException("일치하는 베스트 카테고리가 존재하지 않습니다"))
                .getSideDishes();
    }
}
