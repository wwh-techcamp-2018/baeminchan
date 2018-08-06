package codesquad.dto;

import codesquad.domain.Product;

import java.util.List;

public class SearchRecommendationDto {

    private String keyword;

    private List<Product> products;

    public SearchRecommendationDto() {

    }

    public SearchRecommendationDto(String keyword, List<Product> products) {
        this.keyword = keyword;
        this.products = products;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
