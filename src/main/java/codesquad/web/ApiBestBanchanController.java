package codesquad.web;

import codesquad.domain.Banchan;
import codesquad.domain.BestCategory;
import codesquad.service.BestBanchanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banchan/best")
public class ApiBestBanchanController {

//    @Autowired
//    BestBanchanRepository bestBanchanRepository;
    @Autowired
    BestBanchanService bestBanchanService;
//    @Autowired
//    BanchanRepository banchanRepository;

    @GetMapping("")
    public Iterable<BestCategory> getBestCategories(){
        return bestBanchanService.retrieveAll(); //bestBanchanRepository.findAll();
    }


//    @GetMapping("/test2")
//    public List<BestBanchan> getBestCategories2222(){
//        List<BestBanchan> bestBanchans = new ArrayList<>();
//        bestBanchanRepository.findAll().forEach(bestBanchans::add);
//        return bestBanchans;
//    }

    @GetMapping("/{id}")
    public List<Banchan> getBanchans(@PathVariable Long id){
        return bestBanchanService.getByParentId(id);
    }

}
