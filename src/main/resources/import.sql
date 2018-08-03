INSERT INTO role (id, authority) values (1, 'NORMAL');
INSERT INTO role (id, authority) values (2, 'ADMIN');

INSERT INTO user (id, user_id, password, name, phone_number) values (1, 'gusdk@gusdk.com', '1234qwer!', '권현아', '010-4090-8370');
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
