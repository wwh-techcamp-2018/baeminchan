package codesquad.service;


import codesquad.domain.DomainField;
import codesquad.domain.category.EventCategory;
import codesquad.domain.category.EventCategoryRepository;
import codesquad.domain.product.ProductRepository;
import codesquad.dto.EventCategoryDto;
import codesquad.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryService {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    public void setEventCategoryRepository(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
    }

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "eventCategories")
    public List<EventCategory> readEventCategories() {
        return eventCategoryRepository.findAll();
    }

    @Cacheable(value = "eventCategory", key = "#id")
    public EventCategoryDto readEventCategory(Long id) {
        return new EventCategoryDto(eventCategoryRepository
                .findById(id)
                .orElseThrow(()-> new BadRequestException(DomainField.ID, "해당하는 이벤트 카테고리가 없습니다."))
                , productRepository.findAllByEventCategoryId(id));
    }
}
