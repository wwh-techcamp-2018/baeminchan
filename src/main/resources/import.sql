INSERT INTO User(`user_id`, `email`, `password`, `name`, `phone_number`, `role`, `created_date`, `updated_date`) VALUES('uuid1', 'javajigi@slipp.net', '$2a$10$.gqQMNLPMXmg4henj1HSpO.vHgEzlG1sxreFymURhLRl0urjbhRD6', '자바지기', '010-0000-0000', 'ADMIN', '2018-07-24 16:41:41', '2018-07-24 16:41:41')
INSERT INTO User(`user_id`, `email`, `password`, `name`, `phone_number`, `role`, `created_date`, `updated_date`) VALUES('uuid2', 'sanjigi@slipp.net', '$2a$10$Prhx5fr2qzeJJ.4EjyO0/uHBf7/3E/niBYJRDgrEmLMMQjT25eG6a', '산지기', '010-1111-1111', 'USER', '2018-07-24 16:41:41', '2018-07-24 16:41:41')
INSERT INTO CATEGORY (title) VALUES ('밑반찬');
INSERT INTO CATEGORY (title) VALUES ('국·찌개');
INSERT INTO CATEGORY (title) VALUES ('메인반찬');
INSERT INTO CATEGORY (title) VALUES ('아이반찬');
INSERT INTO CATEGORY (title) VALUES ('정기식단');
INSERT INTO CATEGORY (title) VALUES ('간편식');
INSERT INTO CATEGORY (title) VALUES ('간식');
INSERT INTO CATEGORY (title) VALUES ('브랜드관');
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('무침', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('나물무침', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('볶음', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('조림', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('김치', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('전', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('장아찌·피클', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('젓갈·장·소스', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('세트', 1);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('국', 2);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('찌개', 2);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('탕', 2);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('전골', 2);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('세트', 2);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('고기반찬', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('해산물반찬', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('생선반찬', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('덮밥', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('튀김', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('면', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('양식', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('아시아식', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('분식', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('죽', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('세트', 3);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('이유식 초기/중기', 4);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('이유식 후기/완료기', 4);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('아이반찬', 4);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('어린이반찬', 4);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('간식·음료', 4);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('1~2인', 5);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('3~4인', 5);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('아이반찬', 5);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('간편반찬', 6);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('간편국찌개', 6);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('간편식품', 6);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('베이커리', 7);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('과일', 7);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('주스', 7);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('스무디', 7);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('유제품·커피', 7);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('기타간식', 7);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('반찬가게', 8);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('반찬장인', 8);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('셰프의요리', 8);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('전국맛집', 8);
INSERT INTO CATEGORY (title, parent_category_id) VALUES ('키즈관', 8);
INSERT INTO promotion(id, img_url) VALUES (1, 'https://cdn.bmf.kr/banner/main_banner/180724/1532409236448_d9b2dde93f6a.jpg');
INSERT INTO promotion(id, img_url) VALUES (2, 'https://cdn.bmf.kr/banner/main_banner/180719/1531982599279_e66969d9bd66.jpg');
INSERT INTO promotion(id, img_url) VALUES (3, 'https://cdn.bmf.kr/banner/main_banner/180725/1532508251859_fc64af8aa98c.jpg');
INSERT INTO promotion(id, img_url) VALUES (4, 'https://cdn.bmf.kr/banner/main_banner/180608/1528449480985_4b59a338f5a8.jpg');
INSERT INTO promotion(id, img_url) VALUES (5, 'https://cdn.bmf.kr/banner/main_banner/180719/1531992782374_4c0636e1ca6b.jpg');
INSERT INTO promotion(id, img_url) VALUES (6, 'https://cdn.bmf.kr/banner/main_banner/180328/1522226266924_db98fbaef1ab.jpg');

INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product1', 10000, 50.0, 'product1 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product2', 10000, 50.0, 'product2 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product3', 10000, 50.0, 'product3 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product4', 10000, 50.0, 'product4 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product5', 10000, 50.0, 'product5 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product6', 10000, 50.0, 'product6 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product7', 10000, 50.0, 'product7 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product8', 10000, 50.0, 'product8 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product9', 10000, 50.0, 'product9 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product10', 10000, 50.0, 'product10 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product11', 10000, 50.0, 'product11 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product12', 10000, 50.0, 'product12 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product13', 10000, 50.0, 'product13 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product14', 10000, 50.0, 'product14 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product15', 10000, 50.0, 'product15 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product16', 10000, 50.0, 'product16 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product17', 10000, 50.0, 'product17 desc');
INSERT INTO PRODUCT (category_id, title, original_price, discount_percent, description) VALUES (1, 'product18', 10000, 50.0, 'product18 desc');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (1, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (2, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (3, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (4, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (5, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (6, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (7, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (8, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (9, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (10, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (11, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (12, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (13, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (14, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (15, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (16, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (17, '/img/img-best-dish.jpg');
INSERT INTO PRODUCT_IMAGES (id, images) VALUES (18, '/img/img-best-dish.jpg');
INSERT INTO BEST_PRODUCT (name) VALUES ('best product 1');
INSERT INTO BEST_PRODUCT (name) VALUES ('best product 2');
INSERT INTO BEST_PRODUCT (name) VALUES ('best product 3');
INSERT INTO BEST_PRODUCT (name) VALUES ('best product 4');
INSERT INTO BEST_PRODUCT (name) VALUES ('best product 5');
INSERT INTO BEST_PRODUCT (name) VALUES ('best product 6');
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (1, 1);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (1, 2);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (1, 3);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (2, 4);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (2, 5);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (2, 6);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (3, 7);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (3, 8);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (3, 9);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (4, 10);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (4, 11);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (4, 12);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (5, 13);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (5, 14);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (5, 15);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (6, 16);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (6, 17);
INSERT INTO BEST_PRODUCT_PRODUCTS (best_product_id, products_id) VALUES (6, 18);