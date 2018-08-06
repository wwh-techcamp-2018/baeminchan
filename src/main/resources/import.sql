INSERT INTO USER (uuid, email, password, name, phone_number, role) VALUES ('550e8400-e29b-41d4-a716-446655441111', 'admin@test.com', '$2a$10$N2c924hshyVMg7PyZYjuTOoDZf8acQN.1Yw5kXjDMKB.UpVVk8ZQa', 'admin', '011-1111-1111', 'ADMIN');
INSERT INTO USER (uuid, email, password, name, phone_number, role) VALUES ('550e8400-e29b-41d4-a716-446655442222', 'user@test.com', '$2a$10$N2c924hshyVMg7PyZYjuTOoDZf8acQN.1Yw5kXjDMKB.UpVVk8ZQa', 'user', '011-2222-2222', 'USER');

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


INSERT INTO PROMOTION (img_url) VALUES ('./img/img-main-visual-slide_1.jpg');
INSERT INTO PROMOTION (img_url) VALUES ('./img/img-main-visual-slide_2.jpg');
INSERT INTO PROMOTION (img_url) VALUES ('./img/img-main-visual-slide_3.jpg');
INSERT INTO PROMOTION (img_url) VALUES ('./img/img-main-visual-slide_4.jpg');
INSERT INTO PROMOTION (img_url) VALUES ('./img/img-main-visual-slide_5.jpg');

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

INSERT INTO BEST_PRODUCT (title) VALUES ('best product 1');
INSERT INTO BEST_PRODUCT (title) VALUES ('best product 2');
INSERT INTO BEST_PRODUCT (title) VALUES ('best product 3');
INSERT INTO BEST_PRODUCT (title) VALUES ('best product 4');
INSERT INTO BEST_PRODUCT (title) VALUES ('best product 5');
INSERT INTO BEST_PRODUCT (title) VALUES ('best product 6');

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



