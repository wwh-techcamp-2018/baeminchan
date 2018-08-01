package codesquad.dto;

import codesquad.domain.EventCategory;
import codesquad.domain.Product;

import java.io.Serializable;
import java.util.List;

public class EventCategoryDto implements Serializable {

    private EventCategory eventCategory;

    private List<Product> products;

    public EventCategoryDto() {

    }

    public EventCategoryDto(EventCategory eventCategory, List<Product> products) {
        this.eventCategory = eventCategory;
        this.products = products;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
