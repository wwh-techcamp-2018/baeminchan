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
INSERT INTO category (title, deleted, parent_id) values ('보이면 안됨', true, 1);

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

INSERT INTO tab_category (title) values ('오늘은 뭐먹지');
INSERT INTO tab_category (title) values ('풍성한 고기반찬');
INSERT INTO tab_category (title) values ('바다향가득 반찬');
INSERT INTO tab_category (title) values ('추천해요 아이반찬');
INSERT INTO tab_category (title) values ('할인특가 세트상품');
INSERT INTO tab_category (title) values ('맛있는 간식타임');

insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg',6500,'[집밥의완성] 궁중식 소고기오이감정 370g','여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리',1);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I21A3/a5ae10184ec276667e0a35e6f3012f20.jpg',4500,'[집밥의완성] 매운 돼지가지볶음 380g','이번 주 매콤한 메인요리로 딱!',1);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I6759/7be858b59943e500d72b134e5810bb76.jpg',4500,'[집밥의완성] 오징어 듬뿍 마늘종무침 170g','쫄깃한 오징어가 가득한 여름 별미',1);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg',12000,'[너의반찬] 소고기 육전 250g','양념간장 곁들여서 딱 드셔보세요~',2);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg',8900,'[집밥의완성] 껌승(베트남식 돼지구이) 630g','매콤 짭조름한 소스에 콕!',2);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg',9520,'[슈퍼키친] 버터치킨마커니 300g','이색 치킨 요리? 어렵지 않아요!',2);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg',7200,'[맑을담] 꽁치김치찜 500g','꽁치와 김치의 칼칼한 콜라보',3);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/IBB87/fa527b4914461eefbcdbaa5185c70f05.jpg',5800,'[집반찬연구소] 유자소스 꼬시래기무침 250g','맛, 향, 식감 세 박자가 딱딱 맞아떨어지는 유자소스 꼬시래기무침',3);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I5BD5/dbf4f6c67771ff641f71917bb793c0b1.jpg',17000,'[너의반찬] 양념꽃게장 500g','한입 크기로 다듬어 더욱 먹기 좋아요',3);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I0783/0792c09f383d599777827a3765d1bca8.jpg',4400,'[Young313] 맛간장소스 몽글이 달걀 230g','우유로 부드럽게 만들어 살살 녹는 달걀',4);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/IF088/2dee3415155804a5e9dc7c24fd21d96c.jpg',5850,'[Young313] 비엔나소시지 케첩볶음 200g','한끗차이가 다른 비엔나소시지 케첩볶음',4);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/IA7F1/c4eebd6b6b1feb612bcbaa47c71c4c38.jpg',5400,'[Young313] 어묵채소볶음 250g','부드럽고 순하여 아이들에게 딱맞는 어묵채소볶음',4);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/H8B82/2b1c6be5e548efadb1cab1caba575853.jpg',9900,'[집밥의완성] 맛보기세트','배민찬 베스트 반찬을 골고루',5);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I9A2F/cbd33f62d2e4f3ba31a78ac04ba9ceea.jpg',14700,'[집밥의완성] 태국 세트','카오산로드에서 맛보던 태국의 맛',5);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/I303C/74ed9c0f72999dd8db0769e828e4bc3f.jpg',18000,'[집밥의완성] 베트남 세트','현지 느낌 그대로, 베트남 세트 !',5);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/IC5BB/7db9fa5a56f6203836ae7c90ab67b3ce.jpg',5850,'[정한뿌리죽] 냉호박죽 360g','차갑게 먹는 여름 별미식, 달콤한 호박죽 한 그릇!',6);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/IC927/c65c607751e9f1cb2057092d111d7e78.jpg',1900,'[올반키친] 찰핫도그 80g','모짜렐라 반, 소시지 반!',6);
insert into product(img_url,price,title,discribe,parent_id) values('https://cdn.bmf.kr/_data/product/H5D38/245501ac785549711e399ee0a15714a0.jpg',8700,'[소중한식사] 단팥죽과 몰랑몰랑 인절미 400g','여름별미! 차갑게 먹는 달콤한 단팥죽과 쫄깃한 인절미',6);