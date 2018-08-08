insert into menu (name, child_count, index) values ('menu_root', 8, 0);

insert into menu (name, child_count, index, parent_id) values ('밑반찬', 8, 1, 1);
insert into menu (name, child_count, index, parent_id) values ('국찌개', 4, 2, 1);
insert into menu (name, child_count, index, parent_id) values ('메인반찬', 4, 3, 1);
insert into menu (name, child_count, index, parent_id) values ('아이반찬', 4, 4, 1);
insert into menu (name, child_count, index, parent_id) values ('정기식단', 4, 5, 1);
insert into menu (name, child_count, index, parent_id) values ('신선가공', 4, 6, 1);
insert into menu (name, child_count, index, parent_id) values ('간식', 4, 7, 1);
insert into menu (name, child_count, index, parent_id) values ('브랜드관', 4, 8, 1);

insert into menu (name, child_count, index, parent_id) values ('무침', 0, 1, 2);
insert into menu (name, child_count, index, parent_id) values ('맛있는무침', 0, 2, 2);
insert into menu (name, child_count, index, parent_id) values ('맛없는무침', 0, 3, 2);
insert into menu (name, child_count, index, parent_id) values ('짠무침', 0, 4, 2);
insert into menu (name, child_count, index, parent_id) values ('싱거운무침', 0, 5, 2);
insert into menu (name, child_count, index, parent_id) values ('매운무침', 0, 6, 2);
insert into menu (name, child_count, index, parent_id) values ('뿌링클무침', 0, 7, 2);
insert into menu (name, child_count, index, parent_id) values ('몸에좋은무침', 0, 8, 2);


insert into menu (name, child_count, index, parent_id) values ('33무침', 0, 1, 3);
insert into menu (name, child_count, index, parent_id) values ('33맛있는무침', 0, 2, 3);
insert into menu (name, child_count, index, parent_id) values ('33맛없는무침', 0, 3, 3);
insert into menu (name, child_count, index, parent_id) values ('33짠무침', 0, 4, 3);


insert into menu (name, child_count, index, parent_id) values ('44무침', 0, 1, 4);
insert into menu (name, child_count, index, parent_id) values ('44맛있는무침', 0, 2, 4);
insert into menu (name, child_count, index, parent_id) values ('44맛없는무침', 0, 3, 4);
insert into menu (name, child_count, index, parent_id) values ('44짠무침', 0, 4, 4);


insert into menu (name, child_count, index, parent_id) values ('55무침', 0, 1, 5);
insert into menu (name, child_count, index, parent_id) values ('55맛있는무침', 0, 2, 5);
insert into menu (name, child_count, index, parent_id) values ('55맛없는무침', 0, 3, 5);
insert into menu (name, child_count, index, parent_id) values ('55짠무침', 0, 4, 5);


insert into menu (name, child_count, index, parent_id) values ('66무침', 0, 1, 6);
insert into menu (name, child_count, index, parent_id) values ('66맛있는무침', 0, 2, 6);
insert into menu (name, child_count, index, parent_id) values ('66맛없는무침', 0, 3, 6);
insert into menu (name, child_count, index, parent_id) values ('66짠무침', 0, 4, 6);

insert into menu (name, child_count, index, parent_id) values ('77무침', 0, 1, 7);
insert into menu (name, child_count, index, parent_id) values ('77맛있는무침', 0, 2, 7);
insert into menu (name, child_count, index, parent_id) values ('77맛없는무침', 0, 3, 7);
insert into menu (name, child_count, index, parent_id) values ('77짠무침', 0, 4, 7);

insert into menu (name, child_count, index, parent_id) values ('88무침', 0, 1, 8);
insert into menu (name, child_count, index, parent_id) values ('88맛있는무침', 0, 2, 8);
insert into menu (name, child_count, index, parent_id) values ('88맛없는무침', 0, 3, 8);
insert into menu (name, child_count, index, parent_id) values ('88짠무침', 0, 4, 8);

insert into menu (name, child_count, index, parent_id) values ('99무침', 0, 1, 9);
insert into menu (name, child_count, index, parent_id) values ('99맛있는무침', 0, 2, 9);
insert into menu (name, child_count, index, parent_id) values ('99맛없는무침', 0, 3, 9);
insert into menu (name, child_count, index, parent_id) values ('99짠무침', 0, 4, 9);

insert into promotion(description, url) values ('장윤주 식단 대공개! 그녀의 건강 반찬을 소개합니다.', './img/img-main-visual-slide_1.jpg');
insert into promotion(description, url) values ('스페인 그 식당이 안 부러운 비빔밥&apm;한식요리 기획전.', './img/img-main-visual-slide_2.jpg');
insert into promotion(description, url) values ('3번이미지', './img/img-main-visual-slide_3.jpg');
insert into promotion(description, url) values ('4번이미지', './img/img-main-visual-slide_4.jpg');
insert into promotion(description, url) values ('5번이미지', './img/img-main-visual-slide_5.jpg');

INSERT INTO menu (name, child_count, index) VALUES ('best_root', 6, 0);
insert into menu (name, child_count, index, parent_id) values ('오늘은 뭐먹지', 0, 1, 46);
insert into menu (name, child_count, index, parent_id) values ('풍성한 고기반찬', 0, 2, 46);
insert into menu (name, child_count, index, parent_id) values ('바다향가득 반찬', 0, 3, 46);
insert into menu (name, child_count, index, parent_id) values ('추천해요 아이반찬', 0, 4, 46);
insert into menu (name, child_count, index, parent_id) values ('할인특가 세트상품', 0, 5, 46);
insert into menu (name, child_count, index, parent_id) values ('맛있는 간식타임', 0, 6, 46);

insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg', '[너의반찬] 소고기 육전 250g1', '양념간장 곁들여서 딱 드셔보세요~1', 47);
insert into product (price, thumbnail, title, description, event_menu_id) values (8900, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg', '[집밥의완성] 껌승(베트남식 돼지구이) 630g1', '매콤 짭조름한 소스에 콕!1', 47);
insert into product (price, thumbnail, title, description, event_menu_id) values (9520, 'https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg', '[슈퍼키친] 버터치킨마커니 300g1', '이색 치킨 요리? 어렵지 않아요!1', 47);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg', '[너의반찬] 소고기 육전 250g2', '양념간장 곁들여서 딱 드셔보세요~2', 48);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/IBB87/fa527b4914461eefbcdbaa5185c70f05.jpg', '[집밥의완성] 껌승(베트남식 돼지구이) 630g2', '매콤 짭조름한 소스에 콕!2', 48);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I5BD5/dbf4f6c67771ff641f71917bb793c0b1.jpg', '[슈퍼키친] 버터치킨마커니 300g2', '이색 치킨 요리? 어렵지 않아요!2', 48);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I0783/0792c09f383d599777827a3765d1bca8.jpg', '[너의반찬] 소고기 육전 250g3', '양념간장 곁들여서 딱 드셔보세요~3', 49);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/IF088/2dee3415155804a5e9dc7c24fd21d96c.jpg', '[집밥의완성] 껌승(베트남식 돼지구이) 630g3', '매콤 짭조름한 소스에 콕!3', 49);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/IA7F1/c4eebd6b6b1feb612bcbaa47c71c4c38.jpg', '[슈퍼키친] 버터치킨마커니 300g3', '이색 치킨 요리? 어렵지 않아요!3', 49);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/H8B82/2b1c6be5e548efadb1cab1caba575853.jpg', '[슈퍼키친] 버터치킨마커니 300g4', '양념간장 곁들여서 딱 드셔보세요~4', 50);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg', '[너의반찬] 소고기 육전 250g4', '매콤 짭조름한 소스에 콕!4', 50);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg', '[집밥의완성] 껌승(베트남식 돼지구이) 630g4', '이색 치킨 요리? 어렵지 않아요!4', 50);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg', '[슈퍼키친] 버터치킨마커니 300g5', '양념간장 곁들여서 딱 드셔보세요~5', 51);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg', '[너의반찬] 소고기 육전 250g5', '매콤 짭조름한 소스에 콕!5', 51);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/IBB87/fa527b4914461eefbcdbaa5185c70f05.jpg', '[집밥의완성] 껌승(베트남식 돼지구이) 630g5', '이색 치킨 요리? 어렵지 않아요!5', 51);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I5BD5/dbf4f6c67771ff641f71917bb793c0b1.jpg', '[슈퍼키친] 버터치킨마커니 300g6', '양념간장 곁들여서 딱 드셔보세요~6', 52);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/I0783/0792c09f383d599777827a3765d1bca8.jpg', '[너의반찬] 소고기 육전 250g6', '매콤 짭조름한 소스에 콕!6', 52);
insert into product (price, thumbnail, title, description, event_menu_id) values (12000, 'https://cdn.bmf.kr/_data/product/IF088/2dee3415155804a5e9dc7c24fd21d96c.jpg', '[집밥의완성] 껌승(베트남식 돼지구이) 630g6', '이색 치킨 요리? 어렵지 않아요!6', 52);