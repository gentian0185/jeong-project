This Program make use of Servlet to Board Realization Program


본 프로그램은 JSP & Servlet 을 이용한 게시판 프로그램입니다.
ServletV5는 ajax를 이용한 코멘트 구현을 추가할 예정입니다.


-----------------------------------------------------


 서블릿은 bbs.properties 설정 파일을 통해 각각의 Impl 클래스에 접근할 수 있다.
 
시작 URL :  http://localhost:"포트번호"/"프로젝트명"/list.hs
 
 
 hs.bbs.dao - 쿼리를 입력하고 결과값을 반환하는 DB 접속 관련 패키지.
 hs.bbs 	- 인터페이스 및 사용자 정의 함수 관련 패키지
 hs.bbs.article - 하나의 게시글에 대한 클래스 (읽기 쓰기 답글 수정 등의 기능)
 hs.bbs.file - 파일 관련 클래스. upload만 있으며 download의 경우 servlet 패키지에 
 				따로 또 만들어서 넣었다.
 hs.bbs.list - 초기 보드의 리스트를 뿌려주는 클래스 관련 패키지
 hs.bbs.login - 로그인 관련 패키지. 회원가입창은 미구현이다.
 hs.bbs.servlet - 서블릿 관련 패키지 download 담당이 따로 떨어져있다.





Blog 
		http://blog.naver.com/PostList.nhn?blogId=sdahhj&from=postList&categoryNo=30