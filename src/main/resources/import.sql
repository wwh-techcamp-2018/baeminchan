--— password1 is encrypted to $2a$10$PP4LOohJDx5pJSV.UR.YcuAWM31URSzZOQeilGkNJ5.dLov/mAeS.
INSERT INTO USER (email, password, name, phone_number, permissions ) VALUES("intae@tech.com", "$2a$10$SmlDTzhSsFL5xLBWunPH5.gO6pHfIyFL0dyy8WAIqpGIgxbyAW9Pa",  "intae", "010-1234-5678", 1) ;
--— password1 is encrypted to $2a$10$PP4LOohJDx5pJSV.UR.YcuAWM31URSzZOQeilGkNJ5.dLov/mAeS.
INSERT INTO USER (email, password, name, phone_number, permissions ) VALUES("yeon@tech.com", "$2a$10$SmlDTzhSsFL5xLBWunPH5.gO6pHfIyFL0dyy8WAIqpGIgxbyAW9Pa",  "yeon", "010-1234-5678", 0) ;

-- INSERT INTO CATEGORY (title, parent_id) VALUES("밑반찬", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("국 * 찌개", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("메인반찬", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("아이반찬", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("정기식단", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("간편식", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("간식", NULL);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("브랜드관", NULL);
--
-- INSERT INTO CATEGORY (title, parent_id) VALUES("무침", 1);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("나물무침", 1);
-- INSERT INTO CATEGORY (title, parent_id) VALUES("볶음", 1);

INSERT INTO CATEGORY (title) VALUES ('밑반찬');
INSERT INTO CATEGORY (title) VALUES ('국·찌개');
INSERT INTO CATEGORY (title) VALUES ('메인반찬');
INSERT INTO CATEGORY (title) VALUES ('아이반찬');
INSERT INTO CATEGORY (title) VALUES ('정기식단');
INSERT INTO CATEGORY (title) VALUES ('간편식');
INSERT INTO CATEGORY (title) VALUES ('간식');
INSERT INTO CATEGORY (title) VALUES ('브랜드관');
INSERT INTO CATEGORY (title, parent_id) VALUES ('무침', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('나물무침', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('볶음', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('조림', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('김치', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('전', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('장아찌·피클', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('젓갈·장·소스', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('세트', 1);
INSERT INTO CATEGORY (title, parent_id) VALUES ('국', 2);
INSERT INTO CATEGORY (title, parent_id) VALUES ('찌개', 2);
INSERT INTO CATEGORY (title, parent_id) VALUES ('탕', 2);
INSERT INTO CATEGORY (title, parent_id) VALUES ('전골', 2);
INSERT INTO CATEGORY (title, parent_id) VALUES ('세트', 2);
INSERT INTO CATEGORY (title, parent_id) VALUES ('고기반찬', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('해산물반찬', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('생선반찬', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('덮밥', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('튀김', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('면', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('양식', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('아시아식', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('분식', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('죽', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('세트', 3);
INSERT INTO CATEGORY (title, parent_id) VALUES ('이유식 초기/중기', 4);
INSERT INTO CATEGORY (title, parent_id) VALUES ('이유식 후기/완료기', 4);
INSERT INTO CATEGORY (title, parent_id) VALUES ('아이반찬', 4);
INSERT INTO CATEGORY (title, parent_id) VALUES ('어린이반찬', 4);
INSERT INTO CATEGORY (title, parent_id) VALUES ('간식·음료', 4);
INSERT INTO CATEGORY (title, parent_id) VALUES ('1~2인', 5);
INSERT INTO CATEGORY (title, parent_id) VALUES ('3~4인', 5);
INSERT INTO CATEGORY (title, parent_id) VALUES ('아이반찬', 5);
INSERT INTO CATEGORY (title, parent_id) VALUES ('간편반찬', 6);
INSERT INTO CATEGORY (title, parent_id) VALUES ('간편국찌개', 6);
INSERT INTO CATEGORY (title, parent_id) VALUES ('간편식품', 6);
INSERT INTO CATEGORY (title, parent_id) VALUES ('베이커리', 7);
INSERT INTO CATEGORY (title, parent_id) VALUES ('과일', 7);
INSERT INTO CATEGORY (title, parent_id) VALUES ('주스', 7);
INSERT INTO CATEGORY (title, parent_id) VALUES ('스무디', 7);
INSERT INTO CATEGORY (title, parent_id) VALUES ('유제품·커피', 7);
INSERT INTO CATEGORY (title, parent_id) VALUES ('기타간식', 7);
INSERT INTO CATEGORY (title, parent_id) VALUES ('반찬가게', 8);
INSERT INTO CATEGORY (title, parent_id) VALUES ('반찬장인', 8);
INSERT INTO CATEGORY (title, parent_id) VALUES ('셰프의요리', 8);
INSERT INTO CATEGORY (title, parent_id) VALUES ('전국맛집', 8);
INSERT INTO CATEGORY (title, parent_id) VALUES ('키즈관', 8);


INSERT INTO PRODUCT (title, contents, price, thumbnail_link) VALUES ('고기 반찬', '아침에 먹기 좋은 반찬~~!', 4000, 'https://cdn.bmf.kr/_data/product/IE15B/f4577c1459679650d0554635d921b536.jpg');
INSERT INTO PRODUCT (title, contents, price, thumbnail_link) VALUES ('삼계탕', '맛있는 오골계 삼계탄~!', 40000, 'https://cdn.bmf.kr/_data/product/IE15B/788f07a103bfe8356bb4dcfa9919ba30.jpg');

INSERT INTO EVENT_CATEGORY (title) VALUES ('오늘은 뭐먹지');
INSERT INTO EVENT_CATEGORY (title) VALUES ('풍성한 고기반찬');
INSERT INTO EVENT_CATEGORY (title) VALUES ('바다향가득 반찬');
INSERT INTO EVENT_CATEGORY (title) VALUES ('추천해요 아이반찬');
INSERT INTO EVENT_CATEGORY (title) VALUES ('할인특가 세트상품');
INSERT INTO EVENT_CATEGORY (title) VALUES ('맛있는 간식타임');

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 매운 돼지가지볶음 380g', '이번 주 매콤한 메인요리로 딱!', 4500, 'https://cdn.bmf.kr/_data/product/I21A3/a5ae10184ec276667e0a35e6f3012f20.jpg', 3, 1);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('고기 반찬', '아침에 먹기 좋은 반찬~~!', 4000, 'https://cdn.bmf.kr/_data/product/IE15B/f4577c1459679650d0554635d921b536.jpg', 1, 1);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 궁중식 소고기오이감정 370g', '여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리', 6500, 'https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg', 2, 1);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[너의반찬] 소고기 육전 250g', '양념간장 곁들여서 딱 드셔보세요~', 8000, 'https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg', 2, 2);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 껌승(베트남식 돼지구이) 630g', '매콤 짭조름한 소스에 콕!', 8900, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg', 3, 2);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[슈퍼키친] 버터치킨마커니 300g',  '이색 치킨 요리? 어렵지 않아요!', 9520, 'https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg', 3, 2);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 매운 돼지가지볶음 380g', '이번 주 매콤한 메인요리로 딱!', 4500, 'https://cdn.bmf.kr/_data/product/I21A3/a5ae10184ec276667e0a35e6f3012f20.jpg', 3, 3);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('고기 반찬', '아침에 먹기 좋은 반찬~~!', 4000, 'https://cdn.bmf.kr/_data/product/IE15B/f4577c1459679650d0554635d921b536.jpg', 1, 3);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 궁중식 소고기오이감정 370g', '여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리', 6500, 'https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg', 2, 3);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[너의반찬] 소고기 육전 250g', '양념간장 곁들여서 딱 드셔보세요~', 8000, 'https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg', 2, 4);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 껌승(베트남식 돼지구이) 630g', '매콤 짭조름한 소스에 콕!', 8900, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg', 3, 4);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[슈퍼키친] 버터치킨마커니 300g',  '이색 치킨 요리? 어렵지 않아요!', 9520, 'https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg', 3, 4);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 매운 돼지가지볶음 380g', '이번 주 매콤한 메인요리로 딱!', 4500, 'https://cdn.bmf.kr/_data/product/I21A3/a5ae10184ec276667e0a35e6f3012f20.jpg', 3, 5);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('고기 반찬', '아침에 먹기 좋은 반찬~~!', 4000, 'https://cdn.bmf.kr/_data/product/IE15B/f4577c1459679650d0554635d921b536.jpg', 1, 5);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 궁중식 소고기오이감정 370g', '여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리', 6500, 'https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg', 2, 5);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[너의반찬] 소고기 육전 250g', '양념간장 곁들여서 딱 드셔보세요~', 8000, 'https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg', 2, 6);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 껌승(베트남식 돼지구이) 630g', '매콤 짭조름한 소스에 콕!', 8900, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg', 3, 6);
INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[슈퍼키친] 버터치킨마커니 300g',  '이색 치킨 요리? 어렵지 않아요!', 9520, 'https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg', 3, 6);