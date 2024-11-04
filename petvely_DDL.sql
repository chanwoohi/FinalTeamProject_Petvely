DROP DATABASE IF EXISTS petvely;

CREATE DATABASE IF NOT EXISTS petvely;

USE petvely;

DROP TABLE IF EXISTS `Member`;

CREATE TABLE `Member` (
	`me_num`	int primary key auto_increment	NOT NULL,
	`me_id`	varchar(13) unique	NOT NULL,
	`me_pw`	varchar(255)	NULL,
	`me_nickname`	varchar(20) unique	NULL,
	`me_email`	varchar(50) unique	NULL,
	`me_authority`	varchar(5)	NULL,
	`me_cookie`	varchar(255)	NULL,
	`me_limit`	datetime	NULL,
	`me_loginType`	varchar(10)	NULL,
	`me_addressDetail`	varchar(255)	NULL,
	`me_phone`	varchar(12) unique	NULL,
	`me_wm_point`	int	NULL,
	`me_ga_point`	int	NULL,
	`me_mp_point`	int	NULL,
	`me_total_point`	int	NULL,
	`me_fakeReportNum`	int	NULL,
	`me_ms_status`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `Comment`;

CREATE TABLE `Comment` (
	`cm_num`	int primary key auto_increment	NOT NULL,
	`cm_content`	varchar(200)	NULL,
	`cm_date`	datetime	NULL default current_timestamp,
	`cm_state`	int	NULL,
	`cm_reportCount`	int	NULL,
	`cm_me_num`	int	NOT NULL,
	`cm_po_num`	int	NOT NULL,
	`cm_reply`	int	NOT NULL,
	`cm_ord`	int	NULL,
	`cm_layer`	int	NULL
);

DROP TABLE IF EXISTS `File`;

CREATE TABLE `File` (
	`fi_num`	int primary key auto_increment	NOT NULL,
	`fi_ori_name`	varchar(255)	NULL,
	`fi_name`	varchar(255)	NULL,
	`fi_date`	datetime	NULL,
	`fi_po_num`	int	NOT NULL

);

DROP TABLE IF EXISTS `FacilityShare`;

CREATE TABLE `FacilityShare` (
	`fs_num`	int primary key auto_increment	NOT NULL,
	`fs_name`	varchar(255)	NULL,
	`fs_grade`	int	NULL,
	`fs_reviewCount`	int	NULL,
	`fs_reportCount`	int	NULL,
	`fs_fst_type`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `FacilityReview`;

CREATE TABLE `FacilityReview` (
	`fr_num`	int primary key auto_increment	NOT NULL,
	`fr_content`	longtext	NULL,
	`fr_grade`	int	NULL,
	`fr_date`	datetime	NULL DEFAULT current_timestamp,
	`fr_report`	int	NULL,
	`fr_fs_num`	int	NOT NULL,
	`fr_me_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `Recommend`;

CREATE TABLE `Recommend` (
	`re_num`	int primary key auto_increment	NOT NULL,
	`re_state`	int	NULL,
	`re_me_num`	int	NOT NULL,
	`re_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `GATPost`;

CREATE TABLE `GATPost` (
	`po_num`	int	NOT NULL,
	`gat_gatt_type`	varchar(255)	NOT NULL,
	`gat_startDate`	datetime	NULL default current_timestamp,
	`gat_endDate`	datetime	NULL ,
	`gat_gat`	varchar(1)	NOT NULL,
	`gat_gats_state`	varchar(50)	NOT NULL,
	`gat_emd_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
	`mes_num`	int primary key auto_increment	NOT NULL,
	`mes_content`	longtext	NULL,
	`mes_date`	datetime	NULL,
	`mes_me_receiverNum`	int	NOT NULL,
	`mes_me_senderNum`	int	NOT NULL,
	`mes_readingCheck`	varchar(1)	NULL
);

DROP TABLE IF EXISTS `Report`;

CREATE TABLE `Report` (
	`rp_num`	int primary key auto_increment	NOT NULL,
	`rp_cause`	varchar(255)	NULL,
	`rp_date`	datetime	NULL,
	`rp_me_num`	int NOT NULL,
	`rp_rt_type`	varchar(50)	NOT NULL,
	`rp_rtt_type`	varchar(50)	NOT NULL,
	`rp_rtt_num`	int	NULL
);

DROP TABLE IF EXISTS `ReportType`;

CREATE TABLE `ReportType` (
	`rt_type`	varchar(50)	primary key NOT NULL
);

DROP TABLE IF EXISTS `Animal`;

CREATE TABLE `Animal` (
	`ani_num`	int primary key auto_increment NOT NULL,
	`ani_name`	varchar(40)	NULL,
	`ani_age`	int	NULL,
	`ani_gender`	varchar(1)	NULL,
	`ani_birth`	date	NULL,
	`ani_weight`	float	NULL,
	`ani_img`	longtext	NULL,
	`ani_info`	longtext	NULL,
	`ani_neutralization`	boolean	NULL,
	`ani_at_type`	varchar(50)	NOT NULL,
	`ani_me_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `AnimalType`;

CREATE TABLE `AnimalType` (
	`at_type`	varchar(50) primary key	NOT NULL
);

DROP TABLE IF EXISTS `GoodsType`;

CREATE TABLE `GoodsType` (
	`gt_type`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `Post`;

CREATE TABLE `Post` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`po_title`	varchar(50)	NULL,
	`po_content`	longtext	NULL,
	`po_date`	datetime	NULL default current_timestamp,
	`po_hidden`	varchar(1)	NULL,
	`po_viewCount`	int	NULL,
    `po_notRecommendCount` int NULL,
	`po_recommendCount`	int	NULL,
	`po_reportCount`	int	NULL,
	`po_notice`	varchar(1)	NULL,
	`po_me_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `WalkMatePost`;

CREATE TABLE `WalkMatePost` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`wm_date`	datetime	NULL,
	`wm_time`	varchar(255)	NULL,
	`wm_wms_state`	varchar(50)	NOT NULL DEFAULT "진행중"
);

DROP TABLE IF EXISTS `MarketPost`;

CREATE TABLE `MarketPost` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`mp_gts_state`	varchar(50)	NOT NULL,
	`mp_name`	varchar(50)	NULL,
	`mp_content`	varchar(500)	NULL,
	`mp_price`	int	NULL,
	`mp_gt_type`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `GATType`;

CREATE TABLE `GATType` (
	`gatt_type`	varchar(255)	NOT NULL
);

DROP TABLE IF EXISTS `WalkMateMember`;

CREATE TABLE `WalkMateMember` (
	`wmm_num`	int primary key auto_increment	NOT NULL,
	`wmm_approve`	varchar(1)	NULL,
	`wmm_po_num`	int	NOT NULL,
	`wmm_ani_num`	int	NOT NULL,
	`wmm_me_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `Dibs`;

CREATE TABLE `Dibs` (
	`di_num`	int primary key auto_increment	NOT NULL,
	`di_me_num`	int	NOT NULL,
	`di_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `ReportTargetType`;

CREATE TABLE `ReportTargetType` (
	`rtt_type`	varchar(50)	primary key NOT NULL
);

DROP TABLE IF EXISTS `FacilityShareType`;

CREATE TABLE `FacilityShareType` (
	`fst_type`	varchar(50)	primary key NOT NULL
);

DROP TABLE IF EXISTS `MemberStatus`;

CREATE TABLE `MemberStatus` (
	`ms_status`	varchar(50)	primary key NOT NULL
);

DROP TABLE IF EXISTS `ContentsReview`;

CREATE TABLE `ContentsReview` (
	`cr_num`	int primary key auto_increment	NOT NULL,
	`cr_content`	longtext	NULL,
	`cr_grade`	int	NULL,
	`cr_date`	datetime	NULL default current_timestamp,
	`cr_report`	int	NULL,
	`cr_me_num`	int	NOT NULL,
	`cr_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `GoodsTradeState`;

CREATE TABLE `GoodsTradeState` (
	`gts_state`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `ReportPenaltyStandard`;

CREATE TABLE `ReportPenaltyStandard` (
	`rps_standard`	varchar(50)	primary key NOT NULL,
	`rps_period`	datetime	NULL,
	`rps_punishNumber`	int	NULL,
    `rps_reportNumber` int NULL
);

DROP TABLE IF EXISTS `FriendList`;

CREATE TABLE `FriendList` (
	`fl_num`	int	primary key auto_increment,
	`fl_me_num`	int	NOT NULL,
	`fl_me_num2`	int	NOT NULL
);

DROP TABLE IF EXISTS `BlackList`;

CREATE TABLE `BlackList` (
	`bl_num`	int	primary key auto_increment,
	`bl_me_num`	int	NOT NULL,
	`bl_me_num2`	int	NOT NULL
);

DROP TABLE IF EXISTS `Bookmark`;

CREATE TABLE `Bookmark` (
	`bm_num`	int primary key auto_increment	NOT NULL,
	`bm_me_num`	int	NOT NULL,
	`bm_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `WalkMateState`;

CREATE TABLE `WalkMateState` (
	`wms_state`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `GATState`;

CREATE TABLE `GATState` (
	`gats_state`	varchar(50)	NOT NULL
);

DROP TABLE IF EXISTS `Emd_areas`;

CREATE TABLE `Emd_areas` (
	`emd_num`	int primary key auto_increment	NOT NULL,
	`emd_sigg_num`	int	NOT NULL,
	`emd_areas`	varchar(3)	NULL,
	`emd_name`	varchar(50)	NULL,
	`emd_version`	varchar(20)	NULL
);

DROP TABLE IF EXISTS `Sido_areas`;

CREATE TABLE `Sido_areas` (
	`sido_num`	int primary key auto_increment	NOT NULL,
	`sido_code`	varchar(3)	NULL,
	`sido_name`	varchar(50)	NULL,
	`sido_version`	varchar(20)	NULL
);

DROP TABLE IF EXISTS `Sigg_areas`;

CREATE TABLE `Sigg_areas` (
	`sigg_num`	int primary key auto_increment	NOT NULL,
	`sigg_sido_num`	int	NOT NULL,
	`sigg_code`	varchar(3)	NULL,
	`sigg_name`	varchar(50)	NULL,
	`sigg_version`	varchar(20)	NULL
);

CREATE TABLE `UseAuthority` (
	`ua_num` int primary key auto_increment,
    `ua_postAuthority` boolean NOT NULL DEFAULT TRUE,
    `ua_commentAuthority` boolean NOT NULL DEFAULT TRUE,
    `ua_messageAuthority` boolean NOT NULL DEFAULT TRUE,
    `ua_facilityAuthority` boolean NOT NULL DEFAULT TRUE,
    `ua_reviewAuthority` boolean NOT NULL DEFAULT TRUE,
    `ua_reportAuthority` boolean NOT NULL DEFAULT TRUE
    
);

DROP TABLE IF EXISTS `WalkMatePet`;

CREATE TABLE `WalkMatePet` (
	`wmp_num`	int primary key auto_increment	NOT NULL,
	`ani_num`	varchar(20) NOT NULL
);

DROP TABLE IF EXISTS `PostHostSelectedPets`;

CREATE TABLE PostHostSelectedPets (
    `phsp_num` INT AUTO_INCREMENT PRIMARY KEY,
    `phsp_po_num` INT,              -- 포스트 번호 (PostVO와 연결)
    `phsp_ani_num` INT              -- 선택된 애완동물 번호
);

DROP TABLE IF EXISTS `PostUserSelectedPets`;

CREATE TABLE PostUserSelectedPets (
    `pusp_num` INT AUTO_INCREMENT PRIMARY KEY,
    `pusp_po_num` INT,              -- 포스트 번호 (PostVO와 연결)
    `pusp_ani_num` INT              -- 선택된 애완동물 번호
);

DROP TABLE IF EXISTS `RegEx`;

CREATE TABLE `RegEx` (
        `re_num`        int primary key auto_increment        NOT NULL,
        `re_regex`        varchar(255) NOT NULL
);

DROP TABLE IF EXISTS `Community`;

CREATE TABLE `community` (
        `co_num`        int primary key auto_increment        NOT NULL,
        `co_name`        varchar(50) unique        NULL
);

ALTER TABLE `petvely`.`post` 
ADD COLUMN `po_delete` VARCHAR(1) NULL DEFAULT 0 AFTER `po_me_num`;

ALTER TABLE `petvely`.`post` 
ADD COLUMN `po_co_num` INT NOT NULL AFTER `po_delete`;

ALTER TABLE `petvely`.`marketpost` 
ADD COLUMN `mp_imgUrl` VARCHAR(255) NULL DEFAULT NULL AFTER `mp_gt_type`;

ALTER TABLE `petvely`.`member` 
CHANGE COLUMN `me_authority` `me_authority` VARCHAR(5) NULL DEFAULT 'USER';

#ALTER TABLE `petvely`.`reporttargettype` 
#CHANGE COLUMN `rtt_type` `rtt_type` VARCHAR(50),
#ADD COLUMN `rtt_num` INT PRIMARY KEY AUTO_INCREMENT;

# 신고시간 기준으로 rp_date 저장
ALTER TABLE `Report`
MODIFY `rp_date` DATETIME DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE `petvely`.`animal`
CHANGE COLUMN `ani_age` `ani_age` long;