-- password1 is encrypted to $2a$10$PP4LOohJDx5pJSV.UR.YcuAWM31URSzZOQeilGkNJ5.dLov/mAeS.
INSERT INTO USER (email, password, name, phone_number, permissions ) VALUES("aa@aa.com", "$2a$10$PP4LOohJDx5pJSV.UR.YcuAWM31URSzZOQeilGkNJ5.dLov/mAeS.",  "name", "00-00-00", 1) ;
INSERT INTO USER (email, password, name, phone_number, permissions ) VALUES("admin@admin.com", "$2a$10$PP4LOohJDx5pJSV.UR.YcuAWM31URSzZOQeilGkNJ5.dLov/mAeS.",  "admin", "00-00-00", 0) ;


INSERT INTO CATEGORY (title, parent_id) VALUES("밑반찬", NULL);
INSERT INTO CATEGORY (title, parent_id) VALUES("국 * 찌개", 1);
INSERT INTO CATEGORY (title, parent_id) VALUES("메인반찬", 2);
INSERT INTO CATEGORY (title, parent_id) VALUES("아이반찬", NULL);
INSERT INTO CATEGORY (title, parent_id) VALUES("정기식단", NULL);
INSERT INTO CATEGORY (title, parent_id) VALUES("간편식", NULL);
INSERT INTO CATEGORY (title, parent_id) VALUES("간식", NULL);
INSERT INTO CATEGORY (title, parent_id) VALUES("브랜드관", NULL);



insert into event_category values(1, "오늘은 뭐먹지");
insert into event_category values(2, "풍성한 고기반찬");
insert into event_category values(3, "바다향가득 반찬");
insert into event_category values(4, "추천해요 아이반찬");
insert into event_category values(5, "할인특가 세트상품");
insert into event_category values(6, "맛있는 간식타임");

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 매운 돼지가지볶음 380g', '이번 주 매콤한 메인요리로 딱!', 4500, 'https://cdn.bmf.kr/_data/product/I21A3/a5ae10184ec276667e0a35e6f3012f20.jpg', 3, 1);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('고기 반찬', '아침에 먹기 좋은 반찬~~!', 4000, 'https://cdn.bmf.kr/_data/product/IE15B/f4577c1459679650d0554635d921b536.jpg', 1, 1);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 궁중식 소고기오이감정 370g', '여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리', 6500, 'https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg', 2, 1);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[너의반찬] 소고기 육전 250g', '양념간장 곁들여서 딱 드셔보세요~', 8000, 'https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg', 2, 2);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[집밥의완성] 껌승(베트남식 돼지구이) 630g', '매콤 짭조름한 소스에 콕!', 8900, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg', 3, 2);

INSERT INTO product (title, contents, price, thumbnail_link, category_id, event_category_id) VALUES ('[슈퍼키친] 버터치킨마커니 300g',  '이색 치킨 요리? 어렵지 않아요!', 9520, 'https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg', 3, 2);