package codesquad.support;

import codesquad.product.domain.BestProduct;
import codesquad.product.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class FixtureFactory {
    private static int lastProductIndex = 0;
    private static int lastBestProductIndex = 0;

    public static List<Product> productList(int count) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(createProduct());
        }
        return result;
    }

    public static List<BestProduct> bestProductList(int count, int productSize) {
        List<BestProduct> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            result.add(new BestProduct("Best Product" + lastBestProductIndex, productList(productSize)));
            lastBestProductIndex++;
        }

        return result;
    }

    private static Product createProduct() {
        Product product = Product.builder()
                .title("Product: " + lastProductIndex)
                .category(null)
                .discountPercent(50.0)
                .originalPrice(10000L)
                .description("description")
                .images(null)
                .deliveryType("새벽배송")
                .receiptableDays(null)
                .build();

        lastProductIndex++;

        return product;
    }
}
