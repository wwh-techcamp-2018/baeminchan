INSERT INTO best_category(name) VALUES('오늘은 뭐먹지')
INSERT INTO best_category(name) VALUES('풍성한 고기반찬')
INSERT INTO best_category(name) VALUES('바다향가득 반찬')
INSERT INTO best_category(name) VALUES('추천해요 아이반찬')
INSERT INTO best_category(name) VALUES('할인특가 세트상품')
INSERT INTO best_category(name) VALUES('맛있는 간식타임')


INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛있는 반찬1', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛있는 반찬2', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛있는 반찬3', 1000, 1);

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛없는 반찬1', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛없는 반찬2', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛없는 반찬3', 1000, 2);


INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(1, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(1, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(1, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');