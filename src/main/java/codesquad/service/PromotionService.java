package codesquad.service;

import codesquad.domain.Promotion;
import codesquad.domain.PromotionRepository;
import codesquad.domain.User;
import codesquad.dto.PromotionDto;
import codesquad.exception.NotMatchedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public void create(User sessionUser, PromotionDto promotionDto) {
        promotionRepository.save(promotionDto.toPromotion(sessionUser));
    }

    @Transactional
    @CacheEvict(value = "promotionsCache")
    public void delete(Long id) {
        Promotion promotion = getPromotionById(id);
        promotion.delete();
    }

    @Cacheable(value = "promotionsCache")
    public Iterable<Promotion> gets() {
        return promotionRepository.findByDeletedAndStartDateLessThanEqualAndEndDateGreaterThanEqual(false, LocalDate.now(), LocalDate.now());
    }

    @Transactional
    @CacheEvict(value = "promotionsCache")
    public Promotion update(Long id, PromotionDto updatedPromotionDto) {
        Promotion savedPromotion = getPromotionById(id);
        return savedPromotion.update(updatedPromotionDto);
    }

    private Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id).orElseThrow(() ->  new NotMatchedException("일치하는 프로모션이 없습니다."));
    }
}
