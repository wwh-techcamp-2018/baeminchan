### Todo

- 카테고리 도메인 선언
    - Tree 구조
- 카테고리 데이터 JSON API 제공
- Ajax 로 카테고리 요청 
- 카테고리 HTML 출력
    - 루트 제외한 다른 카테고리 - a 링크 처리
- Interceptor를 통해 특정 url(예를 들어 /admin)로 시작하는 페이지는 관리자 권한을 가진 사용자만 접근
 - Interceptor 로 URI 검사
 - 권한 검사
-  관리자 권한을 가진 사용자는 카테고리를 관리
    - 카테고리 추가, 삭제 요청, 순서 보장
    (+관리 페이지, 프로토타입)
    - ApiAdminController
