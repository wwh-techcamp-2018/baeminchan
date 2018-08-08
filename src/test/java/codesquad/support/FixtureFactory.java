package codesquad.support;

import codesquad.product.domain.BestProduct;
import codesquad.product.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FixtureFactory {
    private static int lastProductIndex = 0;
    private static int lastBestProductIndex = 0;

    public static List<Product> productList(int count) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(createProductBuilder().build());
        }
        return result;
    }

    public static List<Product> productList(String... titles) {
        return Arrays.stream(titles).map(t -> createProductBuilder(t).build()).collect(Collectors.toList());
    }

    public static List<BestProduct> bestProductList(int count, int productSize) {
        List<BestProduct> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            result.add(new BestProduct("Best Product" + lastBestProductIndex, productList(productSize)));
            lastBestProductIndex++;
        }

        return result;
    }

    private static Product.ProductBuilder createProductBuilder(String t) {
        return createProductBuilder().title(t);
    }

    private static Product.ProductBuilder createProductBuilder() {
        Product.ProductBuilder product = Product.builder()
                .title("Product: " + lastProductIndex)
                .category(null)
                .discountPercent(50.0)
                .originalPrice(10000L)
                .description("description")
                .images(null)
                .deliveryType("새벽배송")
                .receiptableDays(null);

        lastProductIndex++;

        return product;
    }
}
