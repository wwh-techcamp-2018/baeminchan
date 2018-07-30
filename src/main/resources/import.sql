INSERT INTO user (uid, email, encrypted_password, name, phone_no, is_admin) values (1, 'a@naver.com', '$2a$10$FeSspuHJEc6mJfAj31SSteOFDbeovPFEQOCnMCZS2Oj7iDuVr7lgy', '자바지기', '000-0000-0000', true);
INSERT INTO user (uid, email, encrypted_password, name, phone_no, is_admin) values (2, 'b@naver.com', '$2a$10$FeSspuHJEc6mJfAj31SSteOFDbeovPFEQOCnMCZS2Oj7iDuVr7lgy', '자바지기2', '000-0000-0000', false);

INSERT INTO category (id, title, deleted) values (0, 'root', false);
INSERT INTO category (id, title, deleted, parent_id) values (1, '밑반찬', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (2, '국,찌개', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (3, '메인반찬', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (4, '아이반찬', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (5, '정기식단', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (6, '간편식', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (7, '간식', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (8, '브랜드관', false, 0);

INSERT INTO category (title, deleted, parent_id) values ('무침', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('나물무침', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('볶음', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('조림', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('김치', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('전', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('장아찌,피클', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('젓갈,장,소스', false, 1);
INSERT INTO category (title, deleted, parent_id) values ('세트', false, 1);

INSERT INTO category (title, deleted, parent_id) values ('국', false, 2);
INSERT INTO category (title, deleted, parent_id) values ('찌개', false, 2);
INSERT INTO category (title, deleted, parent_id) values ('탕', false, 2);
INSERT INTO category (title, deleted, parent_id) values ('전골', false, 2);
INSERT INTO category (title, deleted, parent_id) values ('세트', false, 2);

INSERT INTO category (title, deleted, parent_id) values ('고기반찬', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('해산물반찬', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('생선반찬', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('덮밥', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('튀김', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('면', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('양식', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('아시아식', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('분식', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('죽', false, 3);
INSERT INTO category (title, deleted, parent_id) values ('세트', false, 3);

INSERT INTO category (title, deleted, parent_id) values ('이유식 초기/중기', false, 4);
INSERT INTO category (title, deleted, parent_id) values ('이유식 후기/완료기', false, 4);
INSERT INTO category (title, deleted, parent_id) values ('아이반찬', false, 4);
INSERT INTO category (title, deleted, parent_id) values ('어린이반찬', false, 4);
INSERT INTO category (title, deleted, parent_id) values ('간식,음료', false, 4);

INSERT INTO category (title, deleted, parent_id) values ('1~2인', false, 5);
INSERT INTO category (title, deleted, parent_id) values ('3~4인', false, 5);
INSERT INTO category (title, deleted, parent_id) values ('아이반찬', false, 5);

INSERT INTO category (title, deleted, parent_id) values ('간편반찬', false, 6);
INSERT INTO category (title, deleted, parent_id) values ('간편국찌개', false, 6);
INSERT INTO category (title, deleted, parent_id) values ('간편식품', false, 6);

INSERT INTO category (title, deleted, parent_id) values ('베이커리', false, 7);
INSERT INTO category (title, deleted, parent_id) values ('과일', false, 7);
INSERT INTO category (title, deleted, parent_id) values ('주스', false, 7);
INSERT INTO category (title, deleted, parent_id) values ('스무디', false, 7);
INSERT INTO category (title, deleted, parent_id) values ('유제품,커피', false, 7);
INSERT INTO category (title, deleted, parent_id) values ('기타간식', false, 7);

INSERT INTO category (title, deleted, parent_id) values ('반찬가게', false, 8);
INSERT INTO category (title, deleted, parent_id) values ('반찬장인', false, 8);
INSERT INTO category (title, deleted, parent_id) values ('세프의요리', false, 8);
INSERT INTO category (title, deleted, parent_id) values ('전국맛집', false, 8);
INSERT INTO category (title, deleted, parent_id) values ('키즈관', false, 8);
