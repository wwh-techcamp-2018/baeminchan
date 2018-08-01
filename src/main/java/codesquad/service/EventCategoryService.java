package codesquad.service;


import codesquad.domain.*;
import codesquad.dto.EventCategoryDto;
import codesquad.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventCategoryService {

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<EventCategory> readEventCategories() {
        return eventCategoryRepository.findAll();
    }

    public EventCategoryDto readEventCategory(Long id) {
        return new EventCategoryDto(eventCategoryRepository
                .findById(id)
                .orElseThrow(()-> new BadRequestException(DomainField.ID, "해당하는 이벤트 카테고리가 없습니다."))
                , productRepository.findAllByEventCategoryId(id));
    }
}
