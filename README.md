# 💻 Project 개요
  - 서울시 공공 와이파이 API를 이용해 현재 내 위치에서 가장 가까운 공공 WIFI 20개를 찾아 보여주는 프로젝트

# 🛠️ Tech
  - Java (JakartaEE, Gradle, jdk : Amazon Corretto 21.0.3)
  - Mariadb
  - Tomcat 10.1.26
  - HTML5, CSS3, JSP
  - Lombok, Okhttp3, Gson

# 🗓️ Project 기능 및 실행순서
  1. "Open Api 와이파이 정보 가져오기" 클릭해 서울시 공공 WIFI 위치정보 API 를 요청
  2. Json 으로 받아온 서울시 공공 WIFI 위치정보를 로컬 DB 에 저장
  3. 내 위치의 위도, 경도를 가져와서 내 주변 공공 와이파이 정보 20개를 DB에서 가져와 메인 페이지에 출력
  4. 내 주변 공공 WIFI 검색했던 위치 이력을 조회 및 삭제
  5. 내 주변 공공 WIFI 즐겨찾기(북마크) 생성 / 삭제 기능 및 즐겨찾기(북마크) 그룹 생성 / 수정 / 삭제 기능

# 📌 ERD
<img width="753" alt="public_wifi_erd" src="https://github.com/user-attachments/assets/1a6b7557-05bd-4a8f-a463-3ee10ce0fe9d">

# 📌 Project 시연 영상
https://youtu.be/mNw6Qan7ySI
<img width="2560" alt="스크린샷 2024-08-12 오후 9 03 33" src="https://github.com/user-attachments/assets/fbf01ea8-2431-4d2a-8262-160f5a73b40e">
