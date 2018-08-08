package codesquad.service;

import codesquad.domain.SideDish;
import codesquad.domain.SideDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SideDishService {
    @Autowired
    private SideDishRepository sideDishRepository;
    public List<SideDish> getSearchResults(String searchText) {
        return sideDishRepository.findByNameContaining(searchText).orElse(new ArrayList<>());
    }
}
