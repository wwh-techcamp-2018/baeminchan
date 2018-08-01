package codesquad.promotion.service;

import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }
}
