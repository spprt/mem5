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



-- USER 우선 생성해야함
CREATE TABLE MEMO(
	MEMOID int(11) unsigned NOT NULL AUTO_INCREMENT  COMMENT '메모ID',
    TITLE varchar(255)  COMMENT '제목',
    CONTENT LONGTEXT COMMENT '내용',
    REG_DATE datetime default now() COMMENT '작성일',
    MOD_DATE datetime COMMENT '수정일',
    ISDEL tinyint(1) DEFAULT '0' COMMENT '삭제여부',
    REG_USERID int(11) NOT NULL COMMENT '작성자ID',
   	MOD_USERID int(11) COMMENT '수정자ID',
   	TYPE TINYINT(2) COMMENT '메모타입',
	PRIMARY KEY(MEMOID),
	FOREIGN KEY (REG_USERID) REFERENCES USER (USERID)
);

/**************************
 * 메모공유테이블 MEMO_SHARE
	메모ID	MEMOID	FK	NOT NULL
	사용자ID	USERID	FK	NOT NULL
	사용자명	NAME		
	작성자여부	ISREG		
	분류	CTGRID		
	분류1	CTGR1		
	분류2	CTGR2		
	분류3	CTGR3		
 ***************************/
CREATE TABLE MEMO_SHARE(
	MEMOID int(11) NOT NULL  COMMENT '메모ID',
    USERID int(11) NOT NULL COMMENT '사용자ID',
   	NAME varchar(100) COMMENT '사용자명',
   	ISREG TINYINT(1) COMMENT '작성자여부',
    CTGRID int(11) COMMENT '분류ID',
    CTGR1 int(11)	COMMENT '분류1',
    CTGR2 int(11)	COMMENT '분류2',
    CTGR3 int(11)	COMMENT '분류3',
	FOREIGN KEY (MEMOID) REFERENCES MEMO (MEMOID),
	FOREIGN KEY (USERID) REFERENCES USER (USERID)
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
    CTGRID int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '분류ID',
   	PARENTID int(11)	COMMENT '부모분류ID',
    CTGR1 int(11)	COMMENT '분류1',
    CTGR2 int(11)	COMMENT '분류2',
    CTGR3 int(11)	COMMENT '분류3',
    USERID int(11) NOT NULL COMMENT '사용자ID',
	PRIMARY KEY(CTGRID),
	FOREIGN KEY (USERID) REFERENCES USER (USERID)
);