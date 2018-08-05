INSERT INTO USER (email, encoded_password, name, phone_number,user_authority, created_at) VALUES ('hongjunho@gmail.com', '$2a$10$Iv3haA5kgZfPvfObhRF5BuUtKGqTCu4lLKenyX7XNaKQlTqCDGsVu', '홍준호', '010-1234-1234', 'GENERAL',CURRENT_TIMESTAMP());
INSERT INTO USER (email, encoded_password, name, phone_number,user_authority, created_at) VALUES ('mhyun2@gmail.com', '$2a$10$Iv3haA5kgZfPvfObhRF5BuUtKGqTCu4lLKenyX7XNaKQlTqCDGsVu', '어드민', '010-1234-1234', 'ADMIN',CURRENT_TIMESTAMP());

INSERT INTO CATEGORY (id, name, created_at) VALUES (0, 'ROOT', CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('밑반찬', 0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('국·찌개', 0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('메인반찬',0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('아이반찬', 0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('정기식단',0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('간편식', 0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('간식', 0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('브랜드관', 0, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('무침', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('나물무침', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('볶음', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('조림', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('김치', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('전', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('장아찌·피클', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('젓갈·장·소스', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('세트', 1, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('국', 2, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('찌개', 2, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('탕', 2, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('전골', 2, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('세트', 2, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('고기반찬', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('해산물반찬', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('생선반찬', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('덮밥', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('튀김', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('면', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('양식', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('아시아식', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('분식', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('죽', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('세트', 3, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('이유식 초기/중기', 4, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('이유식 후기/완료기', 4, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('아이반찬', 4, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('어린이반찬', 4, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('간식·음료', 4, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('1~2인', 5, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('3~4인', 5, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('아이반찬', 5, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('간편반찬', 6, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('간편국찌개', 6, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('간편식품', 6, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('베이커리', 7, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('과일', 7, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('주스', 7, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('스무디', 7, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('유제품·커피', 7, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('기타간식', 7, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('반찬가게', 8, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('반찬장인', 8, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('셰프의요리', 8, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('전국맛집', 8, CURRENT_TIMESTAMP());
INSERT INTO CATEGORY (name, parent_id, created_at) VALUES ('키즈관', 8, CURRENT_TIMESTAMP());

INSERT INTO best_banchan (title) VALUES ('오늘은 뭐먹지');
INSERT INTO best_banchan (title) VALUES ('풍성한 고기반찬');
INSERT INTO best_banchan (title) VALUES ('바다향가득 반찬');
INSERT INTO best_banchan (title) VALUES ('추천해요 아이반찬');
INSERT INTO best_banchan (title) VALUES ('할인특가 세트상품');
INSERT INTO best_banchan (title) VALUES ('맛있는 간식타임');


INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I86DF/31c3669a458564e5ebf0a528caae8ce8.jpg' , '[너의반찬] 소고기 육전 250g' , '양념간장 곁들여서 딱 드셔보세요~' , '12,000' , '12,000' , 1);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I5200/da93cbe8aa03e01aaf1a642a4ca54ef1.jpg' , '[집밥의완성] 껌승(베트남식 돼지구이) 630g' , '매콤 짭조름한 소스에 콕!' , '8,900' , '8,800' , 1);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/IF596/20e150430fd19e219062e12383d05491.jpg' , '[슈퍼키친] 버터치킨마커니 300g' , '이색 치킨 요리? 어렵지 않아요!' , '9,520' , '9,520' , 1);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I1BF4/a0954782a02f9e956577a5d8907bad69.jpg' , '[맑을담] 꽁치김치찜 500g' , '꽁치와 김치의 칼칼한 콜라보 ' , '7,200' , '7,200' , 2);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/IBB87/fa527b4914461eefbcdbaa5185c70f05.jpg' , '[집반찬연구소] 유자소스 꼬시래기무침 250g' , '맛, 향, 식감 세 박자가 딱딱 맞아떨어지는 유자소스 꼬시래기무침 ' , '5,400' , '5,220' , 2);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I5BD5/dbf4f6c67771ff641f71917bb793c0b1.jpg' , '[너의반찬] 양념꽃게장 500g' , '한입 크기로 다듬어 더욱 먹기 좋아요' , '17,000' , '13,000' , 2);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I0783/0792c09f383d599777827a3765d1bca8.jpg' , '[Young313] 맛간장소스 몽글이 달걀 230g' , '우유로 부드럽게 만들어 살살 녹는 달걀 ' , '4,400' , '4,000' , 3);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/IF088/2dee3415155804a5e9dc7c24fd21d96c.jpg' , '[Young313] 비엔나소시지 케첩볶음 200g' , '한끗차이가 다른 비엔나소시지 케첩볶음' , '5,850' , '5,850' , 3);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/IA7F1/c4eebd6b6b1feb612bcbaa47c71c4c38.jpg' , '[Young313] 어묵채소볶음 250g' , '부드럽고 순하여 아이들에게 딱맞는 어묵채소볶음' , '5,400' , '5,400' , 3);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/H8B82/2b1c6be5e548efadb1cab1caba575853.jpg' , '[집밥의완성] 맛보기세트' , '배민찬 베스트 반찬을 골고루' , '9,900' , '9,900' , 4);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I9A2F/cbd33f62d2e4f3ba31a78ac04ba9ceea.jpg' , '[집밥의완성] 태국 세트' , '카오산로드에서 맛보던 태국의 맛' , '14,700' , '14,500' , 4);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I303C/74ed9c0f72999dd8db0769e828e4bc3f.jpg' , '[집밥의완성] 베트남 세트' , '현지 느낌 그대로, 베트남 세트 !' , '18,000' , '18,000' , 4);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/IC5BB/7db9fa5a56f6203836ae7c90ab67b3ce.jpg' , '[정한뿌리죽] 냉호박죽 360g' , '차갑게 먹는 여름 별미식, 달콤한 호박죽 한 그릇!' , '5,850' , '5,000' , 5);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/IC927/c65c607751e9f1cb2057092d111d7e78.jpg' , '[올반키친] 찰핫도그 80g' , '모짜렐라 반, 소시지 반! ' , '1,900' , '1,900' , 5);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/H5D38/245501ac785549711e399ee0a15714a0.jpg' , '[소중한식사] 단팥죽과 몰랑몰랑 인절미 400g' , '여름별미! 차갑게 먹는 달콤한 단팥죽과 쫄깃한 인절미' , '8,700' , '8,500' , 5);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg' , '[집밥의완성] 궁중식 소고기오이감정 370g' , '여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리' , '6,500' , '6,000' , 6);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I21A3/a5ae10184ec276667e0a35e6f3012f20.jpg' , '[집밥의완성] 매운 돼지가지볶음 380g' , '이번 주 매콤한 메인요리로 딱!' , '4,200' , '4,000' , 6);
INSERT INTO Banchan (img_url, title, description, original_price, real_price, parent_id) VALUES ('https://cdn.bmf.kr/_data/product/I6759/7be858b59943e500d72b134e5810bb76.jpg' , '[집밥의완성] 오징어 듬뿍 마늘종무침 170g' , '쫄깃한 오징어가 가득한 여름 별미' , '4,500' , '4,500' , 6);