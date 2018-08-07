INSERT INTO User (id, email, password, name, phone, role, deleted) values (1, 'admin@gmail.com', '$2a$10$mz6I2CR3WFud4wYpjdGoIeizrttQPlvO5wb0B/9N0A7kgj8SWNpMa', '관리자', '010-1234-4567','ADMIN',false);
INSERT INTO User (id, email, password, name, phone, role, deleted) values (2, 'javajigi@gmail.com', '$2a$10$mz6I2CR3WFud4wYpjdGoIeizrttQPlvO5wb0B/9N0A7kgj8SWNpMa', '자바지기', '010-8545-6858','DEFAULT',false);

INSERT INTO Category (id, name, parent_id) VALUES (0, 'ROOT', null);
INSERT INTO Category (id, name, parent_id) VALUES (1, '밑반찬', 0);
INSERT INTO Category (id, name, parent_id) VALUES (2, '국・찌개', 0);
INSERT INTO Category (id, name, parent_id) VALUES (3, '메인반찬', 0);
INSERT INTO Category (id, name, parent_id) VALUES (4, '아이반찬', 0);
INSERT INTO Category (id, name, parent_id) VALUES (5, '정기식단', 0);
INSERT INTO Category (id, name, parent_id) VALUES (6, '신선・가공', 0);
INSERT INTO Category (id, name, parent_id) VALUES (7, '간식', 0);
INSERT INTO Category (id, name, parent_id) VALUES (8, '브랜드관', 0);

INSERT INTO Category (name, parent_id) VALUES ('무침', 1);
INSERT INTO Category (name, parent_id) VALUES ('나물무침', 1);
INSERT INTO Category (name, parent_id) VALUES ('볶음', 1);
INSERT INTO Category (name, parent_id) VALUES ('조림', 1);
INSERT INTO Category (name, parent_id) VALUES ('김치', 1);
INSERT INTO Category (name, parent_id) VALUES ('전', 1);
INSERT INTO Category (name, parent_id) VALUES ('장아찌·피클', 1);
INSERT INTO Category (name, parent_id) VALUES ('젓갈·장·소스', 1);
INSERT INTO Category (name, parent_id) VALUES ('세트', 1);
INSERT INTO Category (name, parent_id) VALUES ('국', 2);
INSERT INTO Category (name, parent_id) VALUES ('찌개', 2);
INSERT INTO Category (name, parent_id) VALUES ('탕', 2);
INSERT INTO Category (name, parent_id) VALUES ('전골', 2);
INSERT INTO Category (name, parent_id) VALUES ('세트', 2);
INSERT INTO Category (name, parent_id) VALUES ('고기반찬', 3);
INSERT INTO Category (name, parent_id) VALUES ('해산물반찬', 3);
INSERT INTO Category (name, parent_id) VALUES ('생선반찬', 3);
INSERT INTO Category (name, parent_id) VALUES ('덮밥', 3);
INSERT INTO Category (name, parent_id) VALUES ('튀김', 3);
INSERT INTO Category (name, parent_id) VALUES ('면', 3);
INSERT INTO Category (name, parent_id) VALUES ('양식', 3);
INSERT INTO Category (name, parent_id) VALUES ('아시아식', 3);
INSERT INTO Category (name, parent_id) VALUES ('분식', 3);
INSERT INTO Category (name, parent_id) VALUES ('죽', 3);
INSERT INTO Category (name, parent_id) VALUES ('세트', 3);
INSERT INTO Category (name, parent_id) VALUES ('이유식 초기/중기', 4);
INSERT INTO Category (name, parent_id) VALUES ('이유식 후기/완료기', 4);
INSERT INTO Category (name, parent_id) VALUES ('아이반찬', 4);
INSERT INTO Category (name, parent_id) VALUES ('어린이반찬', 4);
INSERT INTO Category (name, parent_id) VALUES ('간식·음료', 4);
INSERT INTO Category (name, parent_id) VALUES ('1~2인', 5);
INSERT INTO Category (name, parent_id) VALUES ('3~4인', 5);
INSERT INTO Category (name, parent_id) VALUES ('아이반찬', 5);
INSERT INTO Category (name, parent_id) VALUES ('간편반찬', 6);
INSERT INTO Category (name, parent_id) VALUES ('간편국찌개', 6);
INSERT INTO Category (name, parent_id) VALUES ('간편식품', 6);
INSERT INTO Category (name, parent_id) VALUES ('베이커리', 7);
INSERT INTO Category (name, parent_id) VALUES ('과일', 7);
INSERT INTO Category (name, parent_id) VALUES ('주스', 7);
INSERT INTO Category (name, parent_id) VALUES ('스무디', 7);
INSERT INTO Category (name, parent_id) VALUES ('유제품·커피', 7);
INSERT INTO Category (name, parent_id) VALUES ('기타간식', 7);
INSERT INTO Category (name, parent_id) VALUES ('반찬가게', 8);
INSERT INTO Category (name, parent_id) VALUES ('반찬장인', 8);
INSERT INTO Category (name, parent_id) VALUES ('셰프의요리', 8);
INSERT INTO Category (name, parent_id) VALUES ('전국맛집', 8);
INSERT INTO Category (name, parent_id) VALUES ('키즈관', 8);

INSERT INTO Promotion (img_url) VALUES ('./img/img-main-visual-slide_1.jpg');
INSERT INTO Promotion (img_url) VALUES ('./img/img-main-visual-slide_2.jpg');
INSERT INTO Promotion (img_url) VALUES ('./img/img-main-visual-slide_3.jpg');
INSERT INTO Promotion (img_url) VALUES ('./img/img-main-visual-slide_4.jpg');
INSERT INTO Promotion (img_url) VALUES ('./img/img-main-visual-slide_5.jpg');


INSERT INTO Best_Category (id, name) VALUES (1, '서울맛집탐방');
INSERT INTO Best_Category (id, name) VALUES (2, '풍성한 고기반찬');
INSERT INTO Best_Category (id, name) VALUES (3, '바다향가득 반찬');
INSERT INTO Best_Category (id, name) VALUES (4, '간편한 덮밥요리');
INSERT INTO Best_Category (id, name) VALUES (5, '할인특가 세트상품');
INSERT INTO Best_Category (id, name) VALUES (6, '맛있는 간식타임');

INSERT INTO Badge (id, name) VALUES (1, '이벤트특가');
INSERT INTO Badge (id, name) VALUES (2, '베스트');

INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (1, '존맛 김치찌개', '너는 안줄거다', '성열스', 10000, 0);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (2, '곱창사랑', '내꺼임', '갓잇', 5000, 10);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (3, '니들이 게맛을 알아', '너는 안줄거다', '성열스', 2000, 50);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (4, '맥도날드', '너는 안줄거다', '성열스', 2000, 50);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (5, '케이엪씨', '너는 안줄거다', '성열스', 2000, 50);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (6, '석윤곱창', '너는 안줄거다', '성열스', 2000, 50);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (7, '지우히메', '너는 안줄거다', '성열스', 2000, 50);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (8, '혁진퐈이아', '너는 안줄거다', '성열스', 2000, 50);
INSERT INTO Banchan (id, title, sub_title, brand, original_price, sales_rate) VALUES (9, '민성요정', '너는 안줄거다', '성열스', 2000, 50);

INSERT INTO tbl_Badge_Banchan (badge_id, banchan_id) VALUES (1, 1);
INSERT INTO tbl_Badge_Banchan (badge_id, banchan_id) VALUES (2, 1);
INSERT INTO tbl_Badge_Banchan (badge_id, banchan_id) VALUES (1, 2);

INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (1, 1);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (1, 2);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (1, 5);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (2, 2);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (2, 3);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (2, 7);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (3, 1);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (3, 2);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (3, 3);

INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (4, 2);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (4, 8);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (4, 9);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (5, 5);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (5, 6);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (5, 7);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (6, 5);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (6, 7);
INSERT INTO tbl_Bestcategory_Banchan (bestcategory_id, banchan_id) VALUES (6, 9);

