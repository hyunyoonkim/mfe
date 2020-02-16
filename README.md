# MFE - 콘서트 예약 사이트
-----


# 개요
* 프로젝트 명 : MFE(Music For Everyone)
* 일정 : 19.11.13. ~ 19.12.13.(한달) / 유지보수 : 19.12.13 ~
* 팀 구성 : 송민준, 김현윤, 배유리, 조태석, 김바하
* 목적 : 현재 한국에는 기본적인 콘서트 정보를 제공해주는 사이트는 있지만 20~30대의 기호에 맞는 직관적인 UI,
         맞춤 정보를 제공해주는 서비스가 없다. 이에 해외 서비스 중 하나인 feverup을 벤치마킹하여 서비스를 제공한다.
* 사용 기술 및 개발환경
   - O/S : Windows 10
   - Server : Tomcat8.5
   - DB : Orarcle 11g
   - Framework/Library : bootstrap, jQuery, slick
   - Programming Language : JAVA, HTML, JavaScript
   - Tool : Eclipse, Github, AQuerytool, StarUML
# 내용
* [PPT(Slideshare)](https://www.slideshare.net/mjSong9/mfe-project)
* 구현 기능
   - 로그인 / 회원가입
   - 티켓 예매(전자티켓), 취소
   - 검색(키워드, 장르, 지역)
   - 커뮤니티(자유게시판, 리뷰게시판)
   - 실시간 상담서비스(채팅기능 1:N)
   - 마이페이지(찜기능, 개인정보관리, 예매내역, 상담내역)
   - 관리자(회원 정보 관리, 콘서트 정보 관리, 게시판 관리, 상담 관리)
   - 메인화면(맞춤형 콘서트 정보 제공, 최신 콘서트 정보 제공, 장르별 콘서트 정보 제공)
* 팀원별 역할
   - 송민준 : 기획, DB설계, 관리자, 메인화면
   - 김현윤 : 디자인, 마이페이지, 리뷰게시판
   - 배유리 : 자유게시판, 검색, 필터링
   - 조태석 : 회원가입, 로그인, 예매
   - 김바하 : 실시간 상담, 실시간 상담 관리
* 설계의 주안점
   - 기존의 여러 예매 사이트들은 직관성이 떨어진다. UI에서 직관성을 높여 차별화를 높이고 개인별 맞춤형 정보를
      제공하여 20~30대 소비자의 니즈를 충족, VOC를 제공하여 고객관리 업무 효율 극대화를 주안점으로 한다.
* 산출물(대표)
   - ERD
   <img alt="erd" src="https://github.com/dkaskgkdua/MFE/blob/master/WebContent/images/result_image/erd.png">
   - 시퀀스
   <img alt="erd" src="https://github.com/dkaskgkdua/MFE/blob/master/WebContent/images/result_image/sequence.png">
   - 테이블 정의서
   <img alt="erd" src="https://github.com/dkaskgkdua/MFE/blob/master/WebContent/images/result_image/table_defi.png">
   - 유스케이스
   <img alt="erd" src="https://github.com/dkaskgkdua/MFE/blob/master/WebContent/images/result_image/usecase.png">
   - WBS
   <img alt="erd" src="https://github.com/dkaskgkdua/MFE/blob/master/WebContent/images/result_image/wbs.png">
