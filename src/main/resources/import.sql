INSERT INTO CATEGORY (name) VALUES ('밑반찬');
INSERT INTO CATEGORY (name) VALUES ('국·찌개');
INSERT INTO CATEGORY (name) VALUES ('메인반찬');
INSERT INTO CATEGORY (name) VALUES ('아이반찬');
INSERT INTO CATEGORY (name) VALUES ('정기식단');
INSERT INTO CATEGORY (name) VALUES ('간편식');
INSERT INTO CATEGORY (name) VALUES ('간식');
INSERT INTO CATEGORY (name) VALUES ('브랜드관');

INSERT INTO CATEGORY (name, parent_id) VALUES ('무침', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('나물무침', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('볶음', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('조림', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('김치', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('전', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('장아찌·피클', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('젓갈·장·소스', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('세트', 1);
INSERT INTO CATEGORY (name, parent_id) VALUES ('국', 2);
INSERT INTO CATEGORY (name, parent_id) VALUES ('찌개', 2);
INSERT INTO CATEGORY (name, parent_id) VALUES ('탕', 2);
INSERT INTO CATEGORY (name, parent_id) VALUES ('전골', 2);
INSERT INTO CATEGORY (name, parent_id) VALUES ('세트', 2);
INSERT INTO CATEGORY (name, parent_id) VALUES ('고기반찬', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('해산물반찬', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('생선반찬', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('덮밥', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('튀김', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('면', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('양식', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('아시아식', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('분식', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('죽', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('세트', 3);
INSERT INTO CATEGORY (name, parent_id) VALUES ('이유식 초기/중기', 4);
INSERT INTO CATEGORY (name, parent_id) VALUES ('이유식 후기/완료기', 4);
INSERT INTO CATEGORY (name, parent_id) VALUES ('아이반찬', 4);
INSERT INTO CATEGORY (name, parent_id) VALUES ('어린이반찬', 4);
INSERT INTO CATEGORY (name, parent_id) VALUES ('간식·음료', 4);
INSERT INTO CATEGORY (name, parent_id) VALUES ('1~2인', 5);
INSERT INTO CATEGORY (name, parent_id) VALUES ('3~4인', 5);
INSERT INTO CATEGORY (name, parent_id) VALUES ('아이반찬', 5);
INSERT INTO CATEGORY (name, parent_id) VALUES ('간편반찬', 6);
INSERT INTO CATEGORY (name, parent_id) VALUES ('간편국찌개', 6);
INSERT INTO CATEGORY (name, parent_id) VALUES ('간편식품', 6);
INSERT INTO CATEGORY (name, parent_id) VALUES ('베이커리', 7);
INSERT INTO CATEGORY (name, parent_id) VALUES ('과일', 7);
INSERT INTO CATEGORY (name, parent_id) VALUES ('주스', 7);
INSERT INTO CATEGORY (name, parent_id) VALUES ('스무디', 7);
INSERT INTO CATEGORY (name, parent_id) VALUES ('유제품·커피', 7);
INSERT INTO CATEGORY (name, parent_id) VALUES ('기타간식', 7);
INSERT INTO CATEGORY (name, parent_id) VALUES ('반찬가게', 8);
INSERT INTO CATEGORY (name, parent_id) VALUES ('반찬장인', 8);
INSERT INTO CATEGORY (name, parent_id) VALUES ('셰프의요리', 8);
INSERT INTO CATEGORY (name, parent_id) VALUES ('전국맛집', 8);
INSERT INTO CATEGORY (name, parent_id) VALUES ('키즈관', 8);

INSERT INTO best_category(name) VALUES('풍성한 고기반찬')
INSERT INTO best_category(name) VALUES('오늘은 뭐먹지')
INSERT INTO best_category(name) VALUES('바다향가득 반찬')
INSERT INTO best_category(name) VALUES('추천해요 아이반찬')
INSERT INTO best_category(name) VALUES('할인특가 세트상품')
INSERT INTO best_category(name) VALUES('맛있는 간식타임')

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '고구마튀김', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '꼬북칩', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '고구마맛탕', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '고구마줄기볶음', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '프링글스', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '파프리카', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '파무침', 1000, 3);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '파김치', 1000, 3);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '라면', 1000, 3);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '해물라면', 1000, 4);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '떡라면', 1000, 4);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '김치라면', 1000, 4);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '치즈라면', 1000, 5);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '된장국', 1000, 5);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '소구기무국', 1000, 5);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '차돌된장국', 1000, 6);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '미역국', 1000, 6);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '김치찌개', 1000, 6);

INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(1, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(2, 'https://cdn.bmf.kr/_data/product/I21A3/da9a106836a01d411897eb94b37e7c9c.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(3, 'https://cdn.bmf.kr/_data/product/I6759/f95f212332282cb4d3906eaad344c256.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(4, 'https://cdn.bmf.kr/_data/product/IC5BB/7db9fa5a56f6203836ae7c90ab67b3ce.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(5, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(6, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(7, 'https://cdn.bmf.kr/_data/product/H8B82/2b1c6be5e548efadb1cab1caba575853.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(8, 'https://cdn.bmf.kr/_data/product/IC5BB/7db9fa5a56f6203836ae7c90ab67b3ce.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(9, 'https://cdn.bmf.kr/_data/product/H5D38/245501ac785549711e399ee0a15714a0.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(10, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(11, 'https://cdn.bmf.kr/_data/product/I0783/0792c09f383d599777827a3765d1bca8.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(12, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(13, 'https://cdn.bmf.kr/_data/product/H5D38/245501ac785549711e399ee0a15714a0.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(14, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(15, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(16, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(17, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg');
INSERT INTO product_banner_urls(product_id, banner_urls) VALUES(18, 'https://cdn.bmf.kr/_data/product/IA7F1/c4eebd6b6b1feb612bcbaa47c71c4c38.jpg');