INSERT INTO user (uid, email, encrypted_password, name, phone_no, is_admin) values (1, 'a@naver.com', '$2a$10$FeSspuHJEc6mJfAj31SSteOFDbeovPFEQOCnMCZS2Oj7iDuVr7lgy', '자바지기', '000-0000-0000', true);
INSERT INTO user (uid, email, encrypted_password, name, phone_no, is_admin) values (2, 'b@naver.com', '$2a$10$FeSspuHJEc6mJfAj31SSteOFDbeovPFEQOCnMCZS2Oj7iDuVr7lgy', '자바지기2', '000-0000-0000', false);

INSERT INTO category (id, title, deleted) values (0, 'root', false);
INSERT INTO category (id, title, deleted, parent_id) values (1, '밑반찬', false, 0);
INSERT INTO category (id, title, deleted, parent_id) values (2, '아이반찬', false, 0);