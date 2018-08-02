package codesquad.service;

import codesquad.domain.Promotion;
import codesquad.domain.PromotionRepository;
import codesquad.domain.User;
import codesquad.dto.PromotionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService {
    private static final Logger log = LoggerFactory.getLogger(PromotionService.class);

    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getShowList() {
        LocalDate nowDate = LocalDate.now();
        return promotionRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityAsc(nowDate, nowDate);
    }

    @Transactional
    public Promotion create(PromotionDto promotionDto, User user) {
        Promotion promotion = promotionRepository.save(promotionDto.toPromotion(user));
        return promotion;
    }

    @Transactional
    public void update(Long id, PromotionDto promotionDto, User loginUser) {
        getPromotionById(id).update(promotionDto, loginUser);
    }

    @Transactional
    public void delete(Long id, User loginUser) {
        getPromotionById(id).delete();
    }

    private Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
