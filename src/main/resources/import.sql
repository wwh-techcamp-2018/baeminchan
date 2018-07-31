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