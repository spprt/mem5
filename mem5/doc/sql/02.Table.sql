/**************************
 * 회원테이블 MEMO_USER
	회원ID	MEMOID	PK	NOT NULL	
	이메일	EMAIL	NOT NULL		
	닉네임	NAME	NOT NULL		
	가입일	REG_DATE		NOT NULL	SYSDATE
	패스워드	PASSWORD			
 ***************************/
CREATE TABLE MEMO_USER(
	USERID BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '회원ID',
    EMAIL varchar(50) NOT NULL  COMMENT '이메일',
    NAME varchar(10) NOT NULL COMMENT '닉네임',
    PASSWORD varchar(20)  COMMENT '패스워드',
    REG_DATE datetime default now() COMMENT '가입일',
    PRIMARY KEY(USERID)
);

ALTER TABLE makao.MEMO_USER 
DROP COLUMN PASSWORD,
ADD COLUMN OAUTHID VARCHAR(100) NULL AFTER REG_DATE,
ADD COLUMN IMAGE_URL VARCHAR(200) NULL AFTER OAUTHID;


/**************************
 * 메모테이블 MEMO
	메모ID	MEMOID	PK	NOT NULL	
	제목	TITLE			
	내용	CONTENT			
	작성일	REG_DATE		NOT NULL	SYSDATE
	수정일	MOD_DATE			
	삭제여부	ISDEL			0
	작성자	REG_USERID	FK	NOT NULL	
	수정자	MOD_USERID			
	메모타입	TYPE			
 ***************************/
CREATE TABLE MEMO(
	MEMOID BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '메모ID',
    TITLE varchar(255)  COMMENT '제목',
    CONTENT LONGTEXT COMMENT '내용',
    REG_DATE datetime default now() COMMENT '작성일',
    MOD_DATE datetime default now() COMMENT '수정일',
    ISDEL tinyint(1) DEFAULT '0' COMMENT '삭제여부',
    REG_USERID BIGINT(20) NOT NULL COMMENT '작성자ID',
   	MOD_USERID BIGINT(20) COMMENT '수정자ID',
   	TYPE TINYINT(2) COMMENT '메모타입',
   	ISLOCK tynyint(1) DEFAULT '0' COMMENT '잠김여부',
	PRIMARY KEY(MEMOID),
	FOREIGN KEY (REG_USERID) REFERENCES MEMO_USER (USERID)
);


-- ALTER TABLE `memo` CHANGE `mod_date` `mod_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
/**************************
 * 메모공유테이블 MEMO_SHARE
	메모ID	MEMOID	FK	NOT NULL
	사용자ID	USERID	FK	NOT NULL
	사용자명	NAME		
	작성자여부	ISREG
	즐겨찾기여부	ISFAVORITE			
	분류	CTGRID		
	분류1	CTGR1		
	분류2	CTGR2		
	분류3	CTGR3		
 ***************************/
CREATE TABLE MEMO_SHARE(
	SHAREID BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '공유ID',
	MEMOID BIGINT(20) NOT NULL  COMMENT '메모ID',
    USERID BIGINT(20) NOT NULL COMMENT '사용자ID',
   	NAME varchar(100) COMMENT '사용자명',
   	ISREG TINYINT(1) COMMENT '작성자여부',
   	ISFAVORITE TINYINT(1) COMMENT '즐겨찾기여부',
    CTGRID BIGINT(20) COMMENT '분류ID',
    CTGR1 BIGINT(20)	COMMENT '분류1',
    CTGR2 BIGINT(20)	COMMENT '분류2',
    CTGR3 BIGINT(20)	COMMENT '분류3',
    PRIMARY KEY(SHAREID),
	FOREIGN KEY (MEMOID) REFERENCES MEMO (MEMOID),
	FOREIGN KEY (USERID) REFERENCES MEMO_USER (USERID)
);

/**************************
 * 투두테이블 MEMO_TODO
	투두ID	TODOID	PK	NOT NULL
	메모ID	MEMOID	FK	NOT NULL
	투두명일	TITLE
	작성일	REG_DATE		
	인덱스	IDX		
	완료여부	ISCOMP		
 ***************************/

CREATE TABLE MEMO_TODO (
	TODOID BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '투두ID',
	MEMOID BIGINT(20) NOT NULL COMMENT '메모ID',
	TITLE VARCHAR(255) NULL DEFAULT NULL COMMENT '투두명',
	REG_DATE DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
	IDX INT(11) NULL DEFAULT NULL COMMENT '인덱스',
	ISCOMP TINYINT(1) NULL DEFAULT NULL COMMENT '완료여부',
	PRIMARY KEY (TODOID),
	FOREIGN KEY (MEMOID) REFERENCES MEMO (MEMOID)
);


/**************************
 * 분류 테이블 CATEGORY
	분류아이디	CTGRID	PK
	사용자ID	USERID	FK
	분류부모아이디	PARENTID	
	분류1	CTGR1	
	분류2	CTGR2	
	분류3	CTGR3	
***************************/
CREATE TABLE CATEGORY(
    CTGRID BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '분류ID',
   	PARENTID BIGINT(20)	COMMENT '부모분류ID',
   	CTGR_NAME varchar(255)	COMMENT '분류명',
    CTGR1 BIGINT(20)	COMMENT '분류1',
    CTGR2 BIGINT(20)	COMMENT '분류2',
    CTGR3 BIGINT(20)	COMMENT '분류3',
    USERID BIGINT(20) NOT NULL COMMENT '사용자ID',
    IDX	 int(4)	default 0 comment '순서',
	PRIMARY KEY(CTGRID),
	FOREIGN KEY (USERID) REFERENCES MEMO_USER (USERID)
);


/****************************
 * 메모 태그 테이블 MEMO_TAG
	메모ID	MEMOID	FK	NOT NULL
	태그명	TAG		
	순서	IDX		
 ***************************/
CREATE TABLE MEMO_TAG(
	MEMOID BIGINT(20) NOT NULL  COMMENT '메모ID',
   	TAG varchar(100) COMMENT '태그',
    IDX int(4) COMMENT '순서',
	FOREIGN KEY (MEMOID) REFERENCES MEMO (MEMOID)
);