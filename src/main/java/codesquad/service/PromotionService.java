package codesquad.service;

import codesquad.domain.promotion.Promotion;
import codesquad.domain.promotion.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    @Cacheable(value = "promotions")
    public List<Promotion> getPromotions() {
        return promotionRepository.findAll();
    }
}
