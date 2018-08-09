package codesquad.service;

import codesquad.domain.SideDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SideDishService {
    @Autowired
    private SideDishRepository sideDishRepository;

    public Iterable<String> getListByWord(String word) {
        return sideDishRepository.findByNameContaining(word)
                .stream()
                .map((sideDish -> sideDish.getName()))
                .collect(Collectors.toList());
    }
}
