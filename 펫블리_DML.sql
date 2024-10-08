-- Member 테이블에 회원 추가
INSERT INTO petvely.member (me_id, me_pw, me_nickname, me_email, me_authority, me_phone, me_ms_status)
VALUES ('user01', 'password123', 'nickname01', 'user01@example.com', 'user', '01095784512', 'active');

INSERT INTO Member (me_id, me_pw, me_nickname, me_email, me_authority, me_phone, me_ms_status)
VALUES ('user02', 'password456', 'nickname02', 'user02@example.com', 'user', '01085989958', 'active');

-- FacilityShare 테이블에 시설 정보 추가
INSERT INTO FacilityShare (fs_name, fs_grade, fs_reviewCount, fs_reportCount, fs_fst_type)
VALUES ('Pet Park', 5, 10, 0, 'Park');

-- 모든 회원 정보를 조회
SELECT * FROM Member;

-- 특정 회원의 이메일과 닉네임 조회
SELECT me_email, me_nickname FROM Member WHERE me_id = 'user01';

-- 시설 정보에서 리뷰가 5개 이상인 시설만 조회
SELECT fs_name, fs_grade, fs_reviewCount FROM FacilityShare WHERE fs_reviewCount >= 5;

-- 특정 회원의 닉네임을 수정
UPDATE Member
SET me_nickname = 'new_nickname01'
WHERE me_id = 'user01';

-- 특정 회원 삭제
DELETE FROM Member WHERE me_id = 'user01';

INSERT INTO POST ( PO_TITLE, PO_CONTENT, PO_ME_NUM)
	VALUES ( "POST테스트", "ㅎㅇㅇ", "2" );
    
-- 위의 POST의 PO_NUM을 가져와서 WALKMATEPOST에 강제로 넣기

INSERT INTO WALKMATEPOST ( PO_NUM, WM_DATE, WM_TIME )
	VALUES ( 4, '2020-10-19', '19:30');
    
-- auto_increment값 초기화
ALTER TABLE walkmatepost AUTO_INCREMENT = 5;    
ALTER TABLE post AUTO_INCREMENT = 5;

INSERT INTO ANIMAL (ANI_NAME, ANI_AGE, ANI_GENDER, ANI_BIRTH, ANI_WEIGHT, ANI_INFO, ANI_NEUTRALIZATION, ANI_AT_TYPE, ANI_ME_NUM)
	VALUES ( "다초", 7, "M", "2017-05-17", 8.4, "착함", 1, "DOG", 2);