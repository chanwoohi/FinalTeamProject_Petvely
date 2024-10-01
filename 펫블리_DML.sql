-- Member 테이블에 회원 추가
INSERT INTO petvely.Member (me_id, me_pw, me_nickname, me_email, me_authority, me_phone, me_ms_status)
VALUES ('user01', 'password123', 'nickname01', 'user01@example.com', 'user', '01095784512', 'active');

INSERT INTO petvely.Member (me_id, me_pw, me_nickname, me_email, me_authority, me_phone, me_ms_status)
VALUES ('user02', 'password456', 'nickname02', 'user02@example.com', 'user', '01085989958', 'active');

-- 새로운 애플리케이션 런칭 게시글 추가 (user01이 작성)
INSERT INTO petvely.Post (po_title, po_content, po_date, po_hidden, po_viewCount, po_recommendCount, po_reportCount, po_notice, po_me_num)
VALUES 
('강아지 키우면 개추',
'강아지', 
NOW(), 
'0', 
0, 
0, 
0, 
'0', 
(SELECT me_num FROM petvely.Member WHERE me_id = 'user01'));

-- 새로운 애플리케이션 런칭 게시글 추가 (user02이 작성)
INSERT INTO petvely.Post (po_title, po_content, po_date, po_hidden, po_viewCount, po_recommendCount, po_reportCount, po_notice, po_me_num)
VALUES 
('고양이 키우면 개추', 
'고양이',
NOW(), 
'0', 
0, 
0, 
0, 
'0', 
(SELECT me_num FROM petvely.Member WHERE me_id = 'user02'));

-- FacilityShare 테이블에 시설 정보 추가
INSERT INTO petvely.FacilityShare (fs_name, fs_grade, fs_reviewCount, fs_reportCount, fs_fst_type)
VALUES ('Pet Park', 5, 10, 0, 'Park');

-- Animal 테이블에 반려동물 정보 추가
INSERT INTO petvely.Animal (ani_num, ani_name, ani_age, ani_gender, ani_birth, ani_weight, ani_neutralization, ani_at_type, ani_me_num)
VALUES ('A001', 'Max', 2, 'M', '2022-01-01', 5.5, TRUE, 'Dog', (SELECT me_num FROM petvely.Member WHERE me_id = 'user01'));

-- 모든 회원 정보를 조회
SELECT * FROM petvely.Member;

-- 특정 회원의 이메일과 닉네임 조회
SELECT me_email, me_nickname 
FROM petvely.Member 
WHERE me_id = 'user01';

-- 리뷰가 5개 이상인 시설 조회
SELECT fs_name, fs_grade, fs_reviewCount 
FROM petvely.FacilityShare 
WHERE fs_reviewCount >= 5;

-- 특정 회원의 닉네임을 수정
UPDATE petvely.Member
SET me_nickname = 'new_nickname01'
WHERE me_id = 'user01';

-- Animal 테이블에서 반려동물의 무게를 수정
UPDATE petvely.Animal
SET ani_weight = 6.0
WHERE ani_num = 'A001';

-- 특정 회원 삭제
DELETE FROM petvely.Member 
WHERE me_id = 'user01';

-- 특정 반려동물 정보 삭제
DELETE FROM petvely.Animal 
WHERE ani_num = 'A001';



