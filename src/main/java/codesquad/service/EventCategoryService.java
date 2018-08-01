package codesquad.service;

import codesquad.domain.EventCategory;
import codesquad.domain.Product;
import codesquad.exception.NotFoundException;
import codesquad.repository.EventCategoryRepository;
import codesquad.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryService {

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<EventCategory> findAll() {
        return eventCategoryRepository.findAll();
    }

    public List<Product> findProductsByEventCategoryId(Long id) {
        return productRepository.findAllByEventCategoryEquals(
                eventCategoryRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }
}
