CREATE TABLE MFE_BOARD(
	BOARD_NUM          	NUMBER		PRIMARY KEY,         --글 번호(기본키)
	BOARD_NAME         	VARCHAR2(30),   --작성자
	BOARD_PASS          VARCHAR2(30),   --비밀번호
	BOARD_SUBJECT      	VARCHAR2(300),  --제목
	BOARD_CONTENT   	VARCHAR2(4000), --내용
	BOARD_FILE          VARCHAR2(50),   --첨부될 파일 명(시스템에 저장되는 파일명)
	BOARD_ORIGINAL    	VARCHAR2(50),   --첨부될 파일 명(시스템에 저장된 파일의 원래 파일명)
	BOARD_RE_REF        NUMBER,         --답변 글 작성시 참조되는 글의 번호
	BOARD_RE_LEV        NUMBER,         --답변 글의 깊이(원문글:0, 답변:1, 답변의 답변2, 답변의 답변의 답변 :3)
	BOARD_RE_SEQ        NUMBER,         --답변 글의 순서(원문글 기준으로 보여주는 순서)
	BOARD_READCOUNT		NUMBER,         --글의 조회수
	BOARD_DATE 			DATE             		--글의 작성 날짜
);

CREATE TABLE MFE_MEMBER(
	MEMBER_ID 				VARCHAR2(50)	PRIMARY KEY,
	MEMBER_PASSWORD 		VARCHAR2(20) 	NOT NULL,
	MEMBER_NAME 			VARCHAR2(20)	NOT NULL,
	MEMBER_ADDRESS 			VARCHAR2(20) 	NOT NULL,
	MEMBER_PHONE_NUMBER 	VARCHAR2(20) 	NOT NULL,
	MEMBER_PREFERENCE 		VARCHAR2(20),
	MEMBER_GENDER 			VARCHAR2(2) 	NOT NULL
);

CREATE TABLE MFE_CHAT(
   CHAT_LOG_ID 			NUMBER 			PRIMARY KEY,
   CHAT_LOG_ID2			NUMBER,
   MEMBER_ID 			VARCHAR2(50) 	NOT NULL,
   CHAT_LOG_CONTENT 	VARCHAR2(100) 	NOT NULL,
   CHAT_LOG_DATE 		DATE 			NOT NULL,
   FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE
);

CREATE TABLE MFE_LOCAL(
   LOCAL_ID   	NUMBER			PRIMARY KEY,
   LOCAL_NAME   VARCHAR2(20)   NOT NULL
);

CREATE TABLE MFE_GENRE(
   GENRE_ID   	NUMBER			PRIMARY KEY,
   GENRE_NAME   VARCHAR2(20)  	NOT NULL
);

CREATE TABLE MFE_CONCERT(
	CONCERT_ID			NUMBER		PRIMARY KEY,
	CONCERT_NAME		VARCHAR2(200)	NOT NULL,
	CONCERT_DAY			DATE 			NOT NULL,
	CONCERT_MUSICIAN	VARCHAR2(50)	NOT NULL,
	CONCERT_OPEN		VARCHAR2(5)		NOT NULL,
	CONCERT_CLOSE		VARCHAR2(5)		NOT NULL,
	CONCERT_IMAGE		VARCHAR2(100),
	GENRE_ID			NUMBER			NOT NULL,
	LOCAL_ID			NUMBER			NOT NULL,
	CONCERT_PRICE		VARCHAR2(20)	NOT NULL,
	FOREIGN KEY(GENRE_ID) REFERENCES MFE_GENRE ON DELETE CASCADE,
	FOREIGN KEY(LOCAL_ID) REFERENCES MFE_LOCAL ON DELETE CASCADE
);

CREATE TABLE MFE_CARD(
	CARD_ID		VARCHAR2(20)	PRIMARY KEY,
	MEMBER_ID	VARCHAR2(50)	NOT NULL,
	CARD_MMYY	NUMBER			NOT NULL,
	CARD_CVV	NUMBER			NOT NULL,
	FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE
);

CREATE TABLE MFE_BOOK(
	BOOK_ID			NUMBER			PRIMARY KEY,
	CONCERT_ID		NUMBER			NOT NULL,
	MEMBER_ID		VARCHAR2(50)	NOT NULL,
	BOOK_ETICKET	VARCHAR2(20)	NOT NULL,
	BOOK_AMOUNT		NUMBER			NOT NULL,
	CARD_ID			VARCHAR2(20)	NOT NULL,
	BOOK_DATE		DATE			NOT NULL,
	BOOK_PAYMENT	NUMBER			NOT NULL,
	FOREIGN KEY(CONCERT_ID) REFERENCES MFE_CONCERT ON DELETE CASCADE,
	FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE,
	FOREIGN KEY(CARD_ID) REFERENCES MFE_CARD ON DELETE CASCADE
);

CREATE TABLE MFE_SESSID(
	ID 		VARCHAR2(100)
);

CREATE TABLE MFE_LIKEY(
	LIKEY_ID	NUMBER			PRIMARY KEY,
	CONCERT_ID	NUMBER			NOT NULL,
	MEMBER_ID	VARCHAR2(50)	NOT NULL,
	FOREIGN KEY(CONCERT_ID) REFERENCES MFE_CONCERT ON DELETE CASCADE,
	FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE
);

CREATE TABLE MFE_REPLY(
	REPLY_ID			NUMBER 			PRIMARY KEY,
	MEMBER_ID			VARCHAR2(50),
	REPLY_CONTENT		VARCHAR2(200),
	REG_DATE 			DATE			NOT NULL,
	BOARD_RE_REF		NUMBER			NOT NULL,
	FOREIGN KEY(BOARD_RE_REF) REFERENCES MFE_BOARD(BOARD_NUM) ON DELETE CASCADE,
	FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE
);

CREATE TABLE MFE_REVIEW(
	REVIEW_ID			NUMBER			PRIMARY KEY,
	MEMBER_ID			VARCHAR2(50)	NOT NULL,
	REVIEW_PASS			VARCHAR2(20)	NOT NULL,
	CONCERT_ID			NUMBER			NOT NULL,
	REVIEW_TITLE		VARCHAR2(50)	NOT NULL,
	REVIEW_CONTENT		VARCHAR2(1000) 	NOT NULL,
	REVIEW_DATE			DATE			NOT NULL,
	REVIEW_READCOUNT	NUMBER			NOT NULL,
	REVIEW_FILE       	VARCHAR2(50),
	FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE,
	FOREIGN KEY(CONCERT_ID) REFERENCES MFE_CONCERT ON DELETE CASCADE
);

CREATE TABLE MFE_RECENT_SEARCH(
	RECENT_SEARCH_ID		NUMBER			PRIMARY KEY,
	MEMBER_ID				VARCHAR2(20)	NOT NULL,
	RECENT_SEARCH_CONTENT	VARCHAR2(20)	NOT NULL,
	FOREIGN KEY(MEMBER_ID) REFERENCES MFE_MEMBER ON DELETE CASCADE
);



CREATE SEQUENCE MFE_BOARD_SEQ;
CREATE SEQUENCE MFE_BOOK_SEQ;
CREATE SEQUENCE MFE_CARD_SEQ;
CREATE SEQUENCE MFE_CHAT_SEQ;
CREATE SEQUENCE MFE_CHAT_SEQ2;
CREATE SEQUENCE MFE_CONCERT_SEQ;
CREATE SEQUENCE MFE_GENRE_SEQ;
CREATE SEQUENCE MFE_LIKEY_SEQ;
CREATE SEQUENCE MFE_LOCAL_SEQ;
CREATE SEQUENCE MFE_RECENT_SEQ;
CREATE SEQUENCE MFE_REPLY_SEQ;
CREATE SEQUENCE MFE_REVIEW_SEQ;

