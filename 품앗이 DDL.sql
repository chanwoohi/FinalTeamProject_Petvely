select * from gatpost;

INSERT INTO gatpost
(po_num, gat_gatt_type, gat_startDate, gat_endDate, gat_gat, gat_gats_state, gat_emd_num) 
VALUES
	('1', 'ongoing', '2024-10-01', '2024-10-01', 'o', 'ongoing', '1'),
	('2', 'ongoing', '2024-10-02', '2024-10-03', 'o', 'ongoing', '2'),
    ('3', 'ongoing', '2024-10-03', '2024-10-05', 'o', 'ongoing', '3'),
    ('4', 'ongoing', '2024-10-04', '2024-10-07', 'o', 'ongoing', '4'),
    ('5', 'ongoing', '2024-10-05', '2024-10-05', 'o', 'ongoing', '5');
    
select * from emd_areas;
select * from sigg_areas;
select * from sido_areas;

select * 
	from emd_areas
    join sigg_areas
	on emd_sigg_num = sigg_num
	join sido_areas
	on sigg_sido_num = sido_num;
    
INSERT INTO sido_areas(sido_num, sido_code, sido_name, sido_version) 
VALUES
	('1', '11', '서울특별시', '0'), ('2', '21', '부산광역시', '0'), ('3', '22', '대구광역시', '0'),
    ('4', '23', '인천광역시', '0'), ('5', '24', '광주광역시', '0'), ('6', '25', '대전광역시', '0'),
    ('7', '26', '울산광역시', '0'), ('8', '29', '세종특별자치시', '0'), ('9', '31', '경기도', '0'),
    ('10', '32', '강원도', '0'), ('11', '33', '충청북도', '0'), ('12', '34', '충청남도', '0'), 
    ('13', '35', '전라북도', '0'), ('14', '36', '전라남도', '0'), ('15', '37', '경상북도', '0'),
    ('16', '38', '경상남도', '0'), ('17', '39', '제주특별자치도', '0');
    
INSERT INTO sigg_areas(sigg_num, sigg_sido_num, sigg_code, sigg_name, sigg_version) 
VALUES
	('1', 'ongoing', '2024-10-01', '2024-10-01', 'o'),
	('2', 'ongoing', '2024-10-02', '2024-10-03', 'o'),
    ('3', 'ongoing', '2024-10-03', '2024-10-05', 'o'),
    ('4', 'ongoing', '2024-10-04', '2024-10-07', 'o'),
    ('5', 'ongoing', '2024-10-05', '2024-10-05', 'o');
    
INSERT INTO emd_areas(emd_num, emd_sigg_num, emd_areas, emd_name, emd_version) 
VALUES
	('1', 'ongoing', '2024-10-01', '2024-10-01', 'o'),
	('2', 'ongoing', '2024-10-02', '2024-10-03', 'o'),
    ('3', 'ongoing', '2024-10-03', '2024-10-05', 'o'),
    ('4', 'ongoing', '2024-10-04', '2024-10-07', 'o'),
    ('5', 'ongoing', '2024-10-05', '2024-10-05', 'o');