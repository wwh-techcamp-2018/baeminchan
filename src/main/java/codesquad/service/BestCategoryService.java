package codesquad.service;

import codesquad.domain.BestCategory;
import codesquad.repository.BestCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestCategoryService {

    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    public List<BestCategory> findAll() {
        return bestCategoryRepository.findAll();
    }
}
