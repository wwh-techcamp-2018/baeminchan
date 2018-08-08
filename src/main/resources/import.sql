INSERT INTO role (id, authority) values (1, 'NORMAL');
INSERT INTO role (id, authority) values (2, 'ADMIN');

INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (1, 'gusdk@gusdk.com', '1234qwer!', '권현아', '010-4090-8370', 1);
INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (2, 'wngk@wngk.com', '1234qwer!', '박주하', '010-8756-2544', 2);
INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (3, 'test@test.com', '$2a$10$sw/vkQlruxD5zn/IYA6k0uEspUHkiB3GilGkSQLZ2YsNtYE9P8B.W', '테스트', '010-0101-0101', 1);
INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (4, 'admin@admin.com', '$2a$10$sw/vkQlruxD5zn/IYA6k0uEspUHkiB3GilGkSQLZ2YsNtYE9P8B.W', '관리자', '010-0101-0101', 2);

INSERT INTO category (parent_category_id, name, deleted) values (null, '밑반찬', false);
INSERT INTO category (parent_category_id, name, deleted) values (null, '국·찌개', false);
INSERT INTO category (parent_category_id, name, deleted) values (null, '메인반찬', false);
INSERT INTO category (parent_category_id, name, deleted) values (1, '무침', false);
INSERT INTO category (parent_category_id, name, deleted) values (1, '나물무침', false);
INSERT INTO category (parent_category_id, name, deleted) values (1, '볶음', false);
INSERT INTO category (parent_category_id, name, deleted) values (2, '국', false);
INSERT INTO category (parent_category_id, name, deleted) values (2, '찌개', false);
INSERT INTO category (parent_category_id, name, deleted) values (2, '탕', false);
INSERT INTO category (parent_category_id, name, deleted) values (3, '고기반찬', false);
INSERT INTO category (parent_category_id, name, deleted) values (3, '해산물반찬', false);
INSERT INTO category (parent_category_id, name, deleted) values (3, '생선반찬', false);

INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time, priority) values ('주하입니다', '1', 'https://cdn.bmf.kr/banner/main_banner/180724/1532409236448_d9b2dde93f6a.jpg', '2018-01-01', '2019-01-01', '2018-08-01T00:00:00', 2);
INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time, priority) values ('민석입니다.', '2', 'https://cdn.bmf.kr/banner/main_banner/180719/1531982599279_e66969d9bd66.jpg', '2018-01-01', '2019-01-01', '2018-08-01T00:00:00', 1);
INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time, priority) values ('종완입니다.', '1', 'https://cdn.bmf.kr/banner/main_banner/180730/1532935773251_4e3cb565748c.jpg', '2018-01-01', '2019-01-01', '2018-08-01T00:00:00', 9);
INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time) values ('현아입니다.', '2', 'https://cdn.bmf.kr/banner/main_banner/180725/1532508251859_fc64af8aa98c.jpg', '2018-01-01', '2019-01-01', '2018-08-01T00:00:00');
INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time) values ('포비입니다.', '1', 'https://cdn.bmf.kr/banner/main_banner/180608/1528449480985_4b59a338f5a8.jpg', '2018-01-01', '2018-07-01', '2018-08-01T00:00:00');
INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time) values ('크롱입니다.', '2', 'https://cdn.bmf.kr/banner/main_banner/180719/1531992782374_4c0636e1ca6b.jpg', '2018-01-01', '2018-07-01', '2018-08-01T00:00:00');
INSERT INTO promotion (title, user_id, image_url, start_date, end_date, created_time) values ('호눅스입니다.', '1', 'https://cdn.bmf.kr/banner/main_banner/180328/1522226266924_db98fbaef1ab.jpg', '2018-01-01', '2018-07-01', '2018-08-01T00:00:00');


INSERT INTO best_category (name, deleted, writer_id) values ('서울맛집탐방', false, 1);
INSERT INTO best_category (name, deleted, writer_id) values ('풍성한고기반찬', false, 1);
INSERT INTO best_category (name, deleted, writer_id) values ('바다향가득반찬', false, 1);
INSERT INTO best_category (name, deleted, writer_id) values ('간판한덮밥요리', false, 1);
INSERT INTO best_category (name, deleted, writer_id) values ('할인특가세트상품', false, 1);
INSERT INTO best_category (name, deleted, writer_id) values ('맛있는간식타임', false, 1);

INSERT INTO brand (name, address) values ('배달의민족','잠실삼성생명');

INSERT INTO side_dish (name, description, price, sale_price, weight) values ('장조림','이것은 설명입니다', 1000, 500, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('김치','이것은 설명입니다', 5000, 100, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('볶음밥','이것은 설명입니다', 1000, 1000, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('포테토칩','이것은 설명입니다', 100, 50, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('김포카리','이것은 설명입니다', 1000, 800, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('콜김라콜김라콜김라콜','이것은 설명입니다', 200, 100, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('사이김다','이것은 설명입니다', 5000, 3500, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('스프라이트','이것은 설명입니다', 1000, 700, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('사과쥬스','이것은 설명입니다', 1000, 700, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('탕수육','이것은 설명입니다', 1000, 800, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('치킨','이것은 설명입니다', 1000, 900, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('피자','이것은 설명입니다', 5000, 4000, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('햄버거','이것은 설명입니다', 1000, 800, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('허니콤보','이것은 설명입니다', 1000, 500, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('초콜렛','이것은 설명입니다', 2000, 1000, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('아이스크림','이것은 설명입니다', 1000, 100, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('젤리','이것은 설명입니다', 1000, 10, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('계피','이것은 설명입니다', 5000, 1, 100);

INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (1, 1);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (1, 2);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (1, 3);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (2, 4);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (2, 5);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (2, 6);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (3, 7);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (3, 8);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (3, 9);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (4, 10);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (4, 11);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (4, 12);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (5, 13);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (5, 14);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (5, 15);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (6, 16);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (6, 17);
INSERT INTO best_category_side_dishes (best_category_id, side_dishes_id) values (6, 18);