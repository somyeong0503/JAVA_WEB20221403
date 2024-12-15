## * 2주차 수업 *
  - vs code 스프링 부트 설정, 깃허브 연동
  - Thymeleaf
  - url 맵핑, 컨트롤러

####  ➜ 2주차 응용 문제 & 추가 구현 완료
  - url 맵핑, 컨트롤러 추가 : hello.2 맵핑, 5개 속성
<div align="center"> <img width="300" alt="image" src="https://github.com/user-attachments/assets/13ec425d-71fe-41d9-bd9d-795d52c2c5b3"> </div>

 
## * 3주차 수업 *
  - vs code 스프링 부트 설정, 기본 화면 실행, 깃허브 연동
  - Thymeleaf
  - url 맵핑, 컨트롤러 : templates 파일 수정, html 생성, 컨트롤러에 등록

####  ➜ 3주차 응용 문제 & 추가 구현 완료
 - 되돌아 가기 버튼 : 현재 창 닫고, 닫을 시 메세지 출력
   > window.close로 닫기, confirm으로 묻기
<div align="center"> 
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/bf30badb-772a-4b03-be54-517f72d9b6b7">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/c6f15d83-b402-4975-a398-081d11389b7c">
</div>


## * 4주차 수업 *
  - 프로필 업데이트
  - 데이터베이스(spring) 연동

####  ➜ 4주차 응용 문제 & 추가 구현 완료
 - testdb 이름 추가
<p align="center"> 
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/2e3d139e-1f5e-4b4f-becd-4fb5440e16cb">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/272afdcf-92fd-402c-8292-7dbd3a1498ee"> 
</p>


## * 5주차 수업 *
  - 프로필 수정
  - 블로그 게시판 조회
    <p align="center"> <img width="300" alt="image" src="https://github.com/user-attachments/assets/81f72dc2-f3a8-40e7-b0e4-c7f99afb795e"> </p>

####  ➜ 5주차 응용 문제 & 추가 구현 완료
 - @RestController -> @Controller 방식 수정
 - "redirect:/article_~~"; 형식으로 리턴 완료


## * 6주차 수업 *
 - 게시판 글쓰기 수정, 삭제 구현 완료
<p align="center">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/7f533831-3032-46af-94bb-99a5772bfe3d">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/1570e8af-2673-4823-aaf3-301f3263010d">
</p>

####  ➜ 6주차 응용 문제 & 추가 구현 완료
 - 정수 아닌 문자열 매개변수 링크 에러 처리
<p align="center">
 <img width="300" alt="image" src="https://github.com/user-attachments/assets/d0a9a751-6304-49dd-ada8-fce69694c2b8">
 <img width="300" alt="image" src="https://github.com/user-attachments/assets/507e30a9-f15c-49db-b76e-acf289de0be5">
</p>


## * 7주차 수업 *
 - 프로필 프로젝트 수정<br>블로그 게시판 구현 완료(article.html를 재활용하여 board_list.html을 생성후 새게시판을 위한 @GetMapping("/board_list")컨트롤러를 등록하고 새 게시판 DB추가를 위해 domain폴더에 Board.java를 추가해준다. 그리고 리포지토리를 연동후 @GetMapping("/board_view/)새로운 컨트롤러를 등록후 board.view.html을 생성해준다.)
<p align="center">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/61b1c3de-3c79-4111-a65a-93ba3793d18c">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/06142068-2a96-46d5-82d0-8431f740a4cf">
</p>

####  ➜ 7주차 응용 문제 & 추가 구현 완료
 - 게시판 글쓰기 수정 구현 완료(글 수정을 위한 화면을 만든후 수정 동작을 위한 맵핑을 컨트롤러에 등록후 글수정을 위한 board.edit.html을 만들고 AddArticleRequest.java 여기서 title과 content이외 나머지 변수들도 추가 해준다. 마지막으로 BlogService.java중 update 메소드 의 article.getUser(), article.getNewdate(), article.getCount(), article.getLikec();이 4가지 필드를 수정한다.)
<p align="center">
 <img width="300" alt="image" src="https://github.com/user-attachments/assets/77193e6f-bc39-4c60-b511-ffeed20285d4">
</p>


## * 8주차 수업 *
 - 메인화면 게시판 링크 수정(index.html 수정)<br>블로그 글쓰기 페이지 구현(글쓰기 버튼 삽입 후 단순 get 방식으로 글쓰기 페이지 연결, Form 하단에 현재 게시판 필드를 히든 설정 추가)<br>저장 기능 추가(post 방식의 /board_write을 등록 기존 서비스의 save 메소드와 거의 동일하게 구현)<br>검색창 구현 완료(form을 통해 검색어를 get 방식 전송을 한 후 페이징 처리를 위한 서비스 계층 코드를 수정 후 jpa 연동을 위한 리포지토리 수정)
<p align="center">
  <img height="300" src="https://github.com/user-attachments/assets/54c23d07-a5ca-4f5c-b1a6-d4abeab62253" >
  <img height="300" src="https://github.com/user-attachments/assets/7103a512-0770-4fc5-835c-311e85368b90">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/d8954049-9559-441c-ab94-7e920a6340ce">

</p>

####  ➜ 8주차 응용 문제 & 추가 구현 완료
 - 게시판 Id 대신 글번호 출력<br>컨트롤러로 부터 시작 페이지를 전달(int startNum = (page * pageSize) + 1;) 삭제 버튼 구현
<p align="center">
 <img width="400" alt="image" src="https://github.com/user-attachments/assets/b185dad3-6903-49d8-8446-bd5e0b02131f">
 <img height="300" src="https://github.com/user-attachments/assets/07309a42-d2e0-4895-871c-099246bd68d5" >

</p>


## * 9주차 수업 *
 - 회원가입 페이지(회원가입을 위한 테이블을 작성후 Membercontroller.java를 만들어 컨트롤러를 구현후 join_new.html를만들어 연결했다. 그리고 DB연동을 위한 리포지토리를 작성)<br>로그인 기능 구현 완료(Member_login.html을 만들고 컨트롤러와 서비스를 작성하여 연동후 구현하였다.)
<p align="center">
  <img height="200" src="https://github.com/user-attachments/assets/36714319-1a45-4843-941e-baf9ec2abd79">
  <img height="200" src="https://github.com/user-attachments/assets/a5784d14-b8dd-49f9-b066-67c75b2a7c23">

</p>

####  ➜ 9주차 응용 문제 & 추가 구현 완료
 - 입력값 필터링 기능 구현 완료(어노테이션을 이용하여 각 변수 필드를 검증 처리 후 @valid@validated를 추가 수정 해주었다.)
<p align="center">
 <img width="300" alt="image" src="https://github.com/user-attachments/assets/a3aa1fdc-954d-4702-a539-d17ef8eaecd1">
</p>


## * 10주차 수업 *
 - 프로필 팀원 수정 구현(index.html 수정)<br>로그인 세션 유지(고유 세션 ID를 생성하여 개발자 모드에서 UUID 값 확인 유지 구현)<br>검사 기능<br>로그아웃 세션 삭제 구현<br>세션 보안 기능 구현 완료(필터 체인을 통해 보안설정HttpSecurity을 반환)
<p align="center">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/88a287b6-7c6e-42ce-9c38-32b4079734ae">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/42063b42-d585-4682-869d-710b00b0cff5">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/2d80a8b9-99e9-4f7c-b416-d421334a56f0">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/258ca1f1-8ad4-4b76-96da-ac921ef84f94">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/7abdb9bc-829e-4fd8-b46b-ef8064e142f4">
</p>

####  ➜ 10주차 응용 문제 & 추가 구현 완료
 - 게시판 작성자를 사용자로 저장 구현(th:value="${email}"를 사용하여 게시판 작성자를 현재 로그인한 이메일로 저장하도록 구현)<br>글쓰기 수정/삭제 버튼 구현 완료(board.user == loggedInUser를 사용하여 로그인한 사용자와 보드 객체의 사용자가 일치할때만 표시)
<p align="center">
 <img height="300" src="https://github.com/user-attachments/assets/943ace23-51d0-4528-a8a9-b8203d23bb9e" >
</p>

## * 11주차 수업 *
 - 프로필 메일 업로딩 기능(파일을 업로드 위한 post 맵핑을 구현)<br>맵 기능 수정 완료(GPS좌표 수정)<br>깃허브 포트폴리오 웹 사이트 구현 완료 
<p align="center">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/6d2e4e44-92ee-4118-ac97-da2e3f9e5130">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/12ae529a-b707-437b-915e-c2792540d770">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/84abf480-28e3-41da-a98d-44a42b9c740d">
  <img width="300" alt="image" src="https://github.com/user-attachments/assets/676c79b5-8e68-4150-ab9e-3a3251f8e582">
  <img heihgt="80" alt="image" src="https://github.com/user-attachments/assets/51c70f04-7164-4736-86bb-1aa4adb8eaee">

</p>

####  ➜ 11주차 응용 문제 & 추가 구현 완료
 - 로그인,로그아웃 세션 처리 수정(로그인 할때 사용자마다 다른 이름 세션 생성, 로그아웃시 현재 사용자의 세션과 쿠키 삭제)<br>파일 업로드 기능 구현 완료(upload_error.html 페이지를 새로 생성하여 공백이나 오류가 발생시 오류 페이지로 리다이렉트처리)
<p align="center">
 <img width="300" alt="image" src="https://github.com/user-attachments/assets/517d3fc5-fd06-4ba8-8781-3fc92982c343" >
 <img height="200" src="https://github.com/user-attachments/assets/f42358f6-a958-4ec1-8c13-26083286d1da" >
 <img width="300" alt="image" src="https://github.com/user-attachments/assets/d5d44955-fc0b-400e-b715-97ca480f6daf" >
<img width="300" alt="image" src="https://github.com/user-attachments/assets/88ab7fb6-3164-467b-9ac1-c40d438c4ec9" >

</p>
