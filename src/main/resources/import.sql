INSERT INTO role (id, authority) values (1, 'NORMAL');
INSERT INTO role (id, authority) values (2, 'ADMIN');

INSERT INTO user (id, user_id, password, name, phone_number) values (1, 'gusdk@gusdk.com', '1234qwer!', '권현아', '010-4090-8370');
INSERT INTO user (id, user_id, password, name, phone_number) values (2, 'wngk@wngk.com', '1234qwer!', '박주하', '010-8756-2544');
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

