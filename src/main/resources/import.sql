--INSERT INTO member (id, email, password, username, phone_number) values (1, "doy@woowahan.com", "1234password", "doy", "01012345678");
--INSERT INTO member (id, email, password, username, phone_number) values (2, "dooho@woowahan.com", "5678password", "dooho", "01012345678");


INSERT INTO member (email, password, username, phone_number) values ("admin@woowahan.com", "$2a$10$Qowj0RhxvldE.bsJ7hMlbusoPT1D7P1Fe32Kdq/GFZUkRNUAIkoIO", "admin", "01012345678");
INSERT INTO member_roles (member_id, roles) values (1, "DEFAULT");
INSERT INTO member_roles (member_id, roles) values (1, "ADMIN");


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


INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180725/1532508251859_fc64af8aa98c.jpg', '프로모션 메뉴1')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180608/1528449480985_4b59a338f5a8.jpg', '프로모션 메뉴2')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180725/1532496347023_26dfc2acadb2.jpg', '프로모션 메뉴3')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180725/1532509533002_65acd431f094.jpg', '프로모션 메뉴4')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180719/1531982599279_e66969d9bd66.jpg', '프로모션 메뉴5')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180712/1531359067444_c293ab9aa477.jpg', '프로모션 메뉴6')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180719/1531992782374_4c0636e1ca6b.jpg', '프로모션 메뉴7')
INSERT INTO PROMOTION (banner_url, description) VALUES ('https://cdn.bmf.kr/banner/main_banner/180328/1522226266924_db98fbaef1ab.jpg', '프로모션 메뉴8')

INSERT INTO best_category(category_name) VALUES('풍성한 고기반찬')
INSERT INTO best_category(category_name) VALUES('오늘은 뭐먹지')
INSERT INTO best_category(category_name) VALUES('바다향가득 반찬')
INSERT INTO best_category(category_name) VALUES('추천해요 아이반찬')
INSERT INTO best_category(category_name) VALUES('할인특가 세트상품')
INSERT INTO best_category(category_name) VALUES('맛있는 간식타임')

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛있는 반찬1', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛있는 반찬2', 1000, 1);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛있는 반찬3', 1000, 1);

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛없는 반찬1', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛없는 반찬2', 1000, 2);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '맛없는 반찬3', 1000, 2);

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '고기반찬1', 1000, 3);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '고기반찬2', 1000, 3);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '고기반찬3', 1000, 3);

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '해산물 반찬1', 1000, 4);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '해산물 반찬2', 1000, 4);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '해산물 반찬3', 1000, 4);

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '덜익은 반찬1', 1000, 5);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '덜익은 반찬2', 1000, 5);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '덜익은 반찬3', 1000, 5);

INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '감자탕 반찬1', 1000, 6);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '감자탕 반찬2', 1000, 6);
INSERT INTO product(discount_rate, name, price, best_category_id) VALUES(0, '감자탕 반찬3', 1000, 6);


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

INSERT INTO product(name) VALUES('연어');
INSERT INTO product(name) VALUES('여름에 맛있는 냉면');
INSERT INTO product(name) VALUES('연어장');
INSERT INTO product(name) VALUES('자연어처리');
INSERT INTO product(name) VALUES('구운연어');
INSERT INTO product(name) VALUES('훈제연어');
INSERT INTO product(name) VALUES('연어샐러드');
INSERT INTO product(name) VALUES('연어짱');
INSERT INTO product(name) VALUES('연어뱃살튀김');
INSERT INTO product(name) VALUES('연어스테이크');

INSERT INTO product(name) VALUES('피자');
INSERT INTO product(name) VALUES('여름이라면 피자');
INSERT INTO product(name) VALUES('가슴을 활짝 피자');
INSERT INTO product(name) VALUES('치즈피자');
INSERT INTO product(name) VALUES('채식피자');
INSERT INTO product(name) VALUES('오징어 피자');
INSERT INTO product(name) VALUES('피자헛 페퍼로니특가');
INSERT INTO product(name) VALUES('불고기 피자');
INSERT INTO product(name) VALUES('치즈 크러스트 피자');
INSERT INTO product(name) VALUES('피피피자로시작하는말');
INSERT INTO product(name) VALUES('파파스 치킨 피자');

INSERT INTO product(name) VALUES('게살버거');
INSERT INTO product(name) VALUES('연한 게살 버거');
INSERT INTO product(name) VALUES('버거킹 몬스터디럭스');
INSERT INTO product(name) VALUES('모스버거');
INSERT INTO product(name) VALUES('남방 치킨 버거');
INSERT INTO product(name) VALUES('차고 버거');
INSERT INTO product(name) VALUES('치즈버거');
INSERT INTO product(name) VALUES('불고기버거');
INSERT INTO product(name) VALUES('상하이스파이시버거');

INSERT INTO product(name) VALUES('국대떡볶이');
INSERT INTO product(name) VALUES('연어가 들어가있지 않은 떡볶이');
INSERT INTO product(name) VALUES('치즈떡볶이');
INSERT INTO product(name) VALUES('짜장떡볶이');
INSERT INTO product(name) VALUES('엄청매운떡볶이짱매웡');
INSERT INTO product(name) VALUES('국물떡볶이');
INSERT INTO product(name) VALUES('떡볶이튀김순대');

INSERT INTO product(name) VALUES('만두');
INSERT INTO product(name) VALUES('갈비만두');
INSERT INTO product(name) VALUES('김치만두');
INSERT INTO product(name) VALUES('고기만두');
INSERT INTO product(name) VALUES('야채왕만두');
INSERT INTO product(name) VALUES('물만두');
INSERT INTO product(name) VALUES('군만두');
INSERT INTO product(name) VALUES('찐만두');
INSERT INTO product(name) VALUES('카트만두 정통 카레');

