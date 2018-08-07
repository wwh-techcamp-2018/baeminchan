package codesquad.service;

import codesquad.domain.search.SearchItem;
import codesquad.domain.search.SearchItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemService {
    @Autowired
    private SearchItemRepository searchItemRepository;

    @Cacheable(value = "SearchItems")
    public List<SearchItem> findAll(){
        return searchItemRepository.findAll();
    }
}
