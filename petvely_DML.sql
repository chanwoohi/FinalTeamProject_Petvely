use petvely;

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
 
select * from sido_areas;
INSERT INTO sido_areas(sido_num, sido_code, sido_name) 
VALUES
	('1', '11', '서울특별시'), ('2', '26', '부산광역시'), ('3', '27', '대구광역시'),
    ('4', '28', '인천광역시'), ('5', '29', '광주광역시'), ('6', '30', '대전광역시'),
    ('7', '31', '울산광역시'), ('8', '36', '세종특별자치시'), ('9', '41', '경기도'),
    ('10', '51', '강원특별자치도'), ('11', '43', '충청북도'), ('12', '44', '충청남도'), 
    ('13', '52', '전북특별자치도'), ('14', '46', '전라남도'), ('15', '47', '경상북도'),
    ('16', '48', '경상남도'), ('17', '50', '제주특별자치도');

select * from sigg_areas;
INSERT INTO sigg_areas(sigg_num, sigg_sido_num, sigg_code, sigg_name)
VALUES
	('1', '1', '110', '종로구'), ('2', '1', '140', '중구'), ('3', '1', '170', '용산구'), 
    ('4', '1', '200', '성동구'), ('5', '1', '215', '광진구'), ('22', '1', '230', '동대문구'), 
    ('6', '1', '260', '중랑구'), ('7', '1', '290', '성북구'), ('8', '1', '305', '강북구'), 
    ('9', '1', '320', '도봉구'), ('10', '1', '350', '노원구'), ('11', '1', '380', '은평구'), 
    ('23', '1', '410', '서대문구'), ('12', '1', '440', '마포구'), ('13', '1', '470', '양천구'), 
    ('14', '1', '500', '강서구'), ('15', '1', '530', '구로구'), ('16', '1', '545', '금천구'), 
    ('24', '1', '560', '영등포구'), ('17', '1', '590', '동작구'), ('18', '1', '620', '관악구'), 
    ('19', '1', '650', '서초구'), ('20', '1', '680', '강남구'), ('21', '1', '710', '송파구'),     
    ('25', '1', '740', '강동구');

select * from emd_areas;    
INSERT INTO emd_areas(emd_num, emd_sigg_num, emd_areas, emd_name) 
VALUES
	('1', '1', '101', '역삼동'), ('2', '1', '103', '개포동'), ('3', '1', '104', '청담동'), 
    ('4', '1', '105', '삼성동'), ('5', '1', '106', '대치동'), ('6', '1', '107', '신사동'), 
    ('7', '1', '108', '논현동'), ('8', '1', '110', '압구정동'), ('9', '1', '111', '세곡동'), 
    ('10', '1', '112', '자곡동'), ('11', '1', '113', '율현동'), ('12', '1', '114', '일원동'), 
    ('13', '1', '115', '수서동'), ('14', '1', '118', '도곡동'), 
    ('15', '2', '101', '무교동'), ('16', '2', '102', '다동'), ('17', '2', '103', '태평로1가');

select * from gattype;
INSERT INTO gattype(gatt_type) values ('예시1'), ('예시2'),('예시3'), ('예시4'),('예시5'), ('예시6');

select * from gatstate;
INSERT INTO gatstate(gats_state) values ('진행 중'), ('완료');

select * from goodstype;
insert into goodstype values
("먹이"), ("옷"), ("장난감"), ("하우스");

-- Animal 테이블에 반려동물 정보 추가
INSERT INTO petvely.Animal (ani_name, ani_age, ani_gender, ani_birth, ani_weight, ani_neutralization, ani_at_type, ani_me_num)
VALUES 
('다초', 7, 'M', '2017-07-14', 8.4, 0, '강아지', 2),
('잠자리', 1, 'M', '2024-09-18', 0.01, 0, '잠자리', 2),
('호날두', 39, 'M', '1985-02-05', 85, 0, '사람', 3),
('달이', 3, 'W', '2021-05-18', 3, 0, '강아지', 3),
('메시', 37, 'M', '1987-06-25', 67, 0, '사람', 3),
('초코', 1, 'M', '2024-03-03', 3, 0, '강아지', 4);

-- admin 계정 생성 SQL 예시
INSERT INTO member (
    me_id, me_pw, me_nickname, me_email, me_authority, me_cookie, me_limit, 
    me_loginType, me_addressDetail, me_phone, me_wm_point, me_ga_point, 
    me_mp_point, me_total_point, me_fakeReportNum, me_ms_status
) 
VALUES (
    'admin123', -- me_id: 관리자 아이디
    '$2a$10$zoIkkMbb/CH/Ey8bBC0QfuKEEha30LR.56.0/hr4/c/SjseCv3sPe', -- me_pw: 해시된 비밀번호를 사용해야 함
    '관리자', -- me_nickname: 관리자 닉네임
    'admin@example.com', -- me_email: 관리자 이메일
    'admin', -- me_authority: 관리자 권한
    NULL, -- me_cookie: 로그인 쿠키
    NULL, -- me_limit: 로그인 제한 시간
    'local', -- me_loginType: 로그인 타입 (로컬 로그인)
    '서울시 강남구', -- me_addressDetail: 관리자 주소
    '01012345678', -- me_phone: 전화번호
    0, -- me_wm_point: 초기 포인트 (웹 포인트)
    0, -- me_ga_point: 초기 포인트 (게임 포인트)
    0, -- me_mp_point: 초기 포인트 (마일리지 포인트)
    0, -- me_total_point: 초기 총 포인트
    0, -- me_fakeReportNum: 신고 횟수 (관리자이므로 0)
    'active' -- me_ms_status: 관리자 상태 (active 상태)
);

