use petvely;

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