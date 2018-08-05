package codesquad.web;

import codesquad.domain.Banchan;
import codesquad.domain.BestBanchan;
import codesquad.repository.BanchanRepository;
import codesquad.repository.BestBanchanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/banchan/best")
public class ApiBestBanchanController {

    @Autowired
    BestBanchanRepository bestBanchanRepository;

    @Autowired
    BanchanRepository banchanRepository;

    @GetMapping("/test1")
    public Iterable<BestBanchan> getBestCategories(){
        return bestBanchanRepository.findAll();
    }


    @GetMapping("/test2")
    public List<BestBanchan> getBestCategories2222(){
        List<BestBanchan> bestBanchans = new ArrayList<>();
        bestBanchanRepository.findAll().forEach(bestBanchans::add);
        return bestBanchans;
    }

    @GetMapping("/{id}")
    public List<Banchan> getBanchans(@PathVariable Long id){
        return banchanRepository.findByParentId(id);
    }

}
