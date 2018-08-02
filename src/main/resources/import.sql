INSERT INTO role (authority) VALUES ('GENERAL');
INSERT INTO role (authority) VALUES ('ADMIN');
INSERT INTO USER (user_id, password, name, phone_number, role_id) VALUES ('javajigi@naver.com', '$2a$10$7QwTvz1eoED4zuieO5Esp.Lo7tjRUbdxmAmy2jvS/IwjYTi3wMGYi', '재성', '010-1111-2222', 2);
INSERT INTO USER (user_id, password, name, phone_number, role_id) VALUES ('gusdk@naver.com', '$2a$10$7QwTvz1eoED4zuieO5Esp.Lo7tjRUbdxmAmy2jvS/IwjYTi3wMGYi', '현아', '010-1111-2222', 1);

INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '밑반찬', 1, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '국, 찌개', 2, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '메인반찬', 3, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '아이반찬', 4, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '정기식단', 5, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '신선, 가공', 6, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '간식', 7, 1);
INSERT INTO category (parent_category_id, name ,priority, creator_id) VALUES (null, '브랜드관', 8, 1);

INSERT INTO category (parent_category_id, name, creator_id) VALUES (1, '무침', 1);
INSERT INTO category (parent_category_id, name) VALUES (1, '나물무침');
INSERT INTO category (parent_category_id, name) VALUES (1, '볶음');
INSERT INTO category (parent_category_id, name) VALUES (1, '조림');
INSERT INTO category (parent_category_id, name) VALUES (1, '김치');
INSERT INTO category (parent_category_id, name) VALUES (1, '전');
INSERT INTO category (parent_category_id, name) VALUES (1, '튀김');
INSERT INTO category (parent_category_id, name) VALUES (1, '장아찌, 피클');
INSERT INTO category (parent_category_id, name) VALUES (1, '샐러드');
INSERT INTO category (parent_category_id, name) VALUES (1, '김');
INSERT INTO category (parent_category_id, name) VALUES (1, '젓갈, 장, 소스');
INSERT INTO category (parent_category_id, name) VALUES (1, '세트');

INSERT INTO category (parent_category_id, name) VALUES (2, '담백한국');
INSERT INTO category (parent_category_id, name) VALUES (2, '얼큰한국');
INSERT INTO category (parent_category_id, name) VALUES (2, '찌개');
INSERT INTO category (parent_category_id, name) VALUES (2, '탕');
INSERT INTO category (parent_category_id, name) VALUES (2, '전골');
INSERT INTO category (parent_category_id, name) VALUES (2, '세트');

INSERT INTO category (parent_category_id, name) VALUES (3, '고기반찬');
INSERT INTO category (parent_category_id, name) VALUES (3, '해산물반찬');
INSERT INTO category (parent_category_id, name) VALUES (3, '생선반찬');
INSERT INTO category (parent_category_id, name) VALUES (3, '덮밥');
INSERT INTO category (parent_category_id, name) VALUES (3, '면');
INSERT INTO category (parent_category_id, name) VALUES (3, '모던한식');
INSERT INTO category (parent_category_id, name) VALUES (3, '양식');
INSERT INTO category (parent_category_id, name) VALUES (3, '일식, 중식');
INSERT INTO category (parent_category_id, name) VALUES (3, '분식');
INSERT INTO category (parent_category_id, name) VALUES (3, '죽, 스프');
INSERT INTO category (parent_category_id, name) VALUES (3, '세트');

INSERT INTO category (parent_category_id, name) VALUES (4, '이유식 초기/중기');
INSERT INTO category (parent_category_id, name) VALUES (4, '이유식 후기/완료기');
INSERT INTO category (parent_category_id, name) VALUES (4, '아이반찬');
INSERT INTO category (parent_category_id, name) VALUES (4, '어린이반찬');
INSERT INTO category (parent_category_id, name) VALUES (4, '프리미엄재료');
INSERT INTO category (parent_category_id, name) VALUES (4, '간식');
INSERT INTO category (parent_category_id, name) VALUES (4, '음료');

INSERT INTO category (parent_category_id, name) VALUES (5, '1~2인');
INSERT INTO category (parent_category_id, name) VALUES (5, '3~4인');
INSERT INTO category (parent_category_id, name) VALUES (5, '아이반찬');

INSERT INTO category (parent_category_id, name) VALUES (6, '가공반찬');
INSERT INTO category (parent_category_id, name) VALUES (6, '가공간식');
INSERT INTO category (parent_category_id, name) VALUES (6, '냉동');
INSERT INTO category (parent_category_id, name) VALUES (6, '고기, 생선');
INSERT INTO category (parent_category_id, name) VALUES (6, '과일, 채소, 양곡');

INSERT INTO category (parent_category_id, name) VALUES (7, '식사빵');
INSERT INTO category (parent_category_id, name) VALUES (7, '간식빵');
INSERT INTO category (parent_category_id, name) VALUES (7, '케이크, 디저트');
INSERT INTO category (parent_category_id, name) VALUES (7, '주스');
INSERT INTO category (parent_category_id, name) VALUES (7, '스무디');
INSERT INTO category (parent_category_id, name) VALUES (7, '스프레드');
INSERT INTO category (parent_category_id, name) VALUES (7, '유제품, 커피');
INSERT INTO category (parent_category_id, name) VALUES (7, '기타간식');

INSERT INTO category (parent_category_id, name) VALUES (8, '반찬가게');
INSERT INTO category (parent_category_id, name) VALUES (8, '반찬장인');
INSERT INTO category (parent_category_id, name) VALUES (8, '셰프의요리');
INSERT INTO category (parent_category_id, name) VALUES (8, '전국맛집');
INSERT INTO category (parent_category_id, name) VALUES (8, '키즈관');

INSERT INTO promotion (title, user_id, created_time, start_date, end_date, image_url) VALUES ('테스트 프로모션', 1, '2018-08-01', '2018-08-01', '2018-08-01', 'https://cdn.bmf.kr/banner/main_banner/180724/1532409236448_d9b2dde93f6a.jpg');
INSERT INTO promotion (title, user_id, created_time, start_date, end_date, image_url) VALUES ('테스트 프로모션2', 2, '2018-08-01', '2018-08-01', '2018-08-01', 'https://cdn.bmf.kr/banner/main_banner/180724/1532409236448_d9b2dde93f6a.jpg');