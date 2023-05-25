# Final 프로젝트 README.md

# 구현요약

## 개발환경

|  | 프레임워크 | 언어 |
| --- | --- | --- |
| Front-end | Spring Boot(2.7.12) | JAVA8(corretto 1.8 jdk) |
| Back-end | Vue.js(2.7.14) | ES6 |
| Database | MySQL |  |

## 기능요약

| 순번 | 구현 페이지 | 구현여부 | 연관 요구 사항 | 분류 |
| --- | --- | --- | --- | --- |
| 1 | 메인 페이지 | O | F01 | 필수 |
| 2 | 지역별 관광지 검색 기능 구현 | O | F01/ F02/ F03 | 필수 |
| 3 | 회원정보 등록, 수정, 삭제, 조회 기능 구현 | O | F07 | 필수 |
| 4 | 로그인 / 로그아웃 기능 구현 | O | F08 | 필수 |
| 5 | 공지사항 등록, 수정, 삭제, 조회 기능 구현 | O | F09 | 심화 |
| 6 | 공유 게시판 등록, 수정, 삭제, 조회 기능 구현 | O | F10 | 심화 |
| 7 | 여행지 추천  | O | 기타 | 심화 |
| 8 | 댓글 기능 구현 | O | F10/기타 | 심화 |
| 9 | 페이지 에러처리 | O | 기타 |  |

# UML

## Class Diagram

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled.png)

## UseCase

## Trips

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%201.png)

## User

![무제.png](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/무제.png)

# 화면 설계서

<aside>
💡 화면 캡쳐로 대체합니다.

</aside>

## Trips

### Trip Main

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%202.png)

### Trip List

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%203.png)

### Trip Detail

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%204.png)

## Blog

### Blog List

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%205.png)

### Blog Detail

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%206.png)

### Blog Input/Edit

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%207.png)

## Notice

### Notice List

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%208.png)

### Notice Input

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%209.png)

## User

### Login

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%2010.png)

### MyPage

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%2011.png)

## Recommend

![Untitled](Final%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20README%20md%200242b7aa541c41dd8fa57b2d58a5ea33/Untitled%2012.png)