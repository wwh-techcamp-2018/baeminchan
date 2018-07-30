INSERT INTO role (id, authority) values (1, 'NORMAL');
INSERT INTO role (id, authority) values (2, 'ADMIN');

INSERT INTO user (id, user_id, password, name, phone_number) values (1, 'gusdk@gusdk.com', '1234qwer!', '권현아', '010-4090-8370');
INSERT INTO user (id, user_id, password, name, phone_number) values (2, 'wngk@wngk.com', '1234qwer!', '박주하', '010-8756-2544');

INSERT INTO category (parent_category_id, name) values (null, '밑반찬');
INSERT INTO category (parent_category_id, name) values (null, '국·찌개');
INSERT INTO category (parent_category_id, name) values (null, '메인반찬');
INSERT INTO category (parent_category_id, name) values (1, '무침');
INSERT INTO category (parent_category_id, name) values (1, '나물무침');
INSERT INTO category (parent_category_id, name) values (1, '볶음');
INSERT INTO category (parent_category_id, name) values (2, '국');
INSERT INTO category (parent_category_id, name) values (2, '찌개');
INSERT INTO category (parent_category_id, name) values (2, '탕');
INSERT INTO category (parent_category_id, name) values (3, '고기반찬');
INSERT INTO category (parent_category_id, name) values (3, '해산물반찬');
INSERT INTO category (parent_category_id, name) values (3, '생선반찬');

