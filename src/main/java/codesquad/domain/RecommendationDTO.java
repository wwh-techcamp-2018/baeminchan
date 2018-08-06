package codesquad.domain;

import java.util.ArrayList;
import java.util.List;

public class RecommendationDTO {

    private String keyword;
    private List<Product> products;

    public RecommendationDTO() {

    }

    public RecommendationDTO(String keyword, List<Product> products) {
        this.keyword = keyword;
        this.products = products;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static RecommendationDTO valueOf(String keyword){
        return new RecommendationDTO(keyword, new ArrayList<Product>());
    }
}
