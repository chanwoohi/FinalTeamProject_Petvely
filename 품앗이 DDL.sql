select * from gatpost
	left join emd_areas on gat_emd_num = emd_num
	left join sigg_areas on emd_sigg_num = sigg_num
	left join sido_areas on sigg_sido_num = sido_num
	left join post using (po_num)
    left join member on po_me_num = me_num;

select * from post left join member on po_me_num = me_num;

select * from gatpost;
INSERT INTO gatpost
(po_num, gat_gatt_type, gat_startDate, gat_endDate, gat_gat, gat_gats_state, gat_emd_num) 
VALUES 
	('1', 'ongoing', '2024-10-01', '2024-10-01', 'w', 'ongoing', '15'),
    ('2', 'ongoing', '2024-10-01', '2024-10-01', 'w', 'ongoing', '10'),
    ('3', 'ongoing', '2024-10-01', '2024-10-01', 'w', 'ongoing', '17'),
	('4', 'ongoing', '2024-10-01', '2024-10-01', 'w', 'ongoing', '3');

select * from post;
INSERT INTO post(po_num, po_title, po_content, po_me_num) 
	VALUES 
		('1', 'test1', 'w', '1'), ('2', 'test2', 'w', '1'), ('3', 'test2', 'w', '1'),
        ('4', '테스트1', 'w', '1');

select * from member;
INSERT INTO member (me_id, me_pw, me_nickname, me_email, me_authority, me_phone, me_ms_status)
	VALUES ('user01', 'password123', 'nickname01', 'user01@example.com', 'user', '01095784512', 'active');
select * from gatpost
	join post using (po_num);
    
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