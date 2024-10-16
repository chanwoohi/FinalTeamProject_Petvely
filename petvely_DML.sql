-- Member 테이블에 회원 추가
INSERT INTO petvely.Member (me_id, me_pw, me_nickname, me_email, me_authority, me_phone, me_ms_status)
VALUES 
('user01', 'password111', 'nickname01', 'user01@example.com', 'user', '01011111111', 'active'), 
('user02', 'password222', 'nickname02', 'user02@example.com', 'user', '01022222222', 'active'), 
('user03', 'password333', 'nickname03', 'user03@example.com', 'user', '01033333333', 'active'), 
('user04', 'password444', 'nickname04', 'user04@example.com', 'user', '01044444444', 'active'), 
('user05', 'password555', 'nickname05', 'user05@example.com', 'user', '01055555555', 'active');

-- 새로운 게시글 추가 (user01이 작성)
INSERT INTO petvely.Post (po_title, po_content, po_date, po_co_num, po_hidden, po_viewCount, po_recommendCount, po_reportCount, po_notice, po_me_num, po_delete)
VALUES 
('고양이 키우면 게추', 
 '히히',
 NOW(), 
 1,        -- 커뮤니티 번호 (정수로 입력)
 0,        -- 숨김 여부 (0: 숨김 아님)
 0,        -- 조회수
 0,        -- 추천 수
 0,        -- 신고 수
 'N',      -- 공지 여부 (N: 공지 아님)
 (SELECT me_num FROM petvely.Member WHERE me_id = 'user01'), -- user01의 회원 번호 조회
 0         -- 삭제 여부 (0: 삭제되지 않음)
);

-- 새로운 게시글 추가 (user02이 작성)
INSERT INTO petvely.Post (po_title, po_content, po_date, po_co_num, po_hidden, po_viewCount, po_recommendCount, po_reportCount, po_notice, po_me_num, po_delete)
VALUES 
('강아지 키우면 게추', 
 '히히',
 NOW(), 
 1,        -- 커뮤니티 번호 (정수로 입력)
 0,        -- 숨김 여부 (0: 숨김 아님)
 0,        -- 조회수
 0,        -- 추천 수
 0,        -- 신고 수
 'N',      -- 공지 여부 (N: 공지 아님)
 (SELECT me_num FROM petvely.Member WHERE me_id = 'user02'), -- user02의 회원 번호 조회
 0         -- 삭제 여부 (0: 삭제되지 않음)
);

# 관리자가 '강아지', '고양이', '도마뱀', '고슴도치' 커뮤니티를 추가했을 때 필요한 쿼리
INSERT INTO COMMUNITY(CO_NAME) VALUES('강아지');
INSERT INTO COMMUNITY(CO_NAME) VALUES('고양이');
INSERT INTO COMMUNITY(CO_NAME) VALUES('도마뱀');
INSERT INTO COMMUNITY(CO_NAME) VALUES('고슴도치');

insert into petvely.regex(re_regex)
values
("/^\w{3,13}$/"), ("/^[a-zA-Z0-9!@#$]{3,15}$/"), ("/^01(\d{1})[-. )]*(\d{4})[-. ]*(\d{4})$/");

insert into petvely.memberstatus(ms_status)
values
("활동"), ("정지");
