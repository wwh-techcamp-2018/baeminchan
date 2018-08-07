package codesquad.service;

import codesquad.domain.Banchan;
import codesquad.domain.BestCategory;
import codesquad.repository.BestBanchanRepository;
import codesquad.support.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestBanchanService {
    @Autowired
    private BestBanchanRepository bestBanchanRepository;
    //@Autowired
    //private BanchanRepository banchanRepository; .findAllByParentId
//todo cacheable
//    @Cacheable
    public List<Banchan> getByParentId(Long id) {
       BestCategory bestCategory = bestBanchanRepository.findById(id)
               .orElseThrow( () -> new NotExistException("Best Banchan Not Found"));
       return bestCategory.getBanchans();
    }
//todo cacheable
//    @Cacheable
    public Iterable<BestCategory> retrieveAll() {
        return bestBanchanRepository.findAll();
    }

    //create, delete
}
