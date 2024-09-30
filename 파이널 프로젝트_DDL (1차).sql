CREATE TABLE `Member` (
	`me_num`	int primary key auto_increment	NOT NULL,
	`me_id`	varchar(13) unique	NOT NULL,
	`me_pw`	varchar(20)	NULL,
	`me_nickname`	varchar(20) unique	NULL,
	`me_email`	varchar(50) unique	NULL,
	`me_authority`	varchar(5)	NULL,
	`me_cookie`	varchar(255)	NULL,
	`me_limit`	datetime	NULL,
	`me_loginType`	varchar(10)	NULL,
	`me_addressDetail`	varchar(255)	NULL,
	`me_phone`	varchar(8) unique	NULL,
	`me_wm_point`	int	NULL,
	`me_ga_point`	int	NULL,
	`me_mp_point`	int	NULL,
	`me_total_point`	int	NULL,
	`me_fakeReportNum`	int	NULL,
	`me_ms_status`	varchar(50)	NOT NULL
);

CREATE TABLE `Comment` (
	`cm_num`	int primary key auto_increment	NOT NULL,
	`cm_content`	varchar(200)	NULL,
	`cm_date`	datetime	NULL,
	`cm_state`	int	NULL,
	`cm_reportCount`	int	NULL,
	`cm_me_num`	int	NOT NULL,
	`cm_mp_num`	int	NOT NULL,
	`cm_reply`	int	NOT NULL,
	`cm_ord`	int	NULL,
	`cm_layer`	int	NULL
);

CREATE TABLE `File` (
	`fi_num`	int primary key auto_increment	NOT NULL,
	`fi_ori_name`	varchar(255)	NULL,
	`fi_name`	varchar(255)	NULL,
	`fi_date`	datetime	NULL,
	`fi_po_num`	int	NOT NULL
);

CREATE TABLE `FacilityShare` (
	`fs_num`	int primary key auto_increment	NOT NULL,
	`fs_name`	varchar(255)	NULL,
	`fs_grade`	int	NULL,
	`fs_reviewCount`	int	NULL,
	`fs_reportCount`	int	NULL,
	`fs_fst_type`	varchar(50)	NOT NULL
);

CREATE TABLE `FacilityReview` (
	`fr_num`	int primary key auto_increment	NOT NULL,
	`fr_content`	longtext	NULL,
	`fr_grade`	int	NULL,
	`fr_date`	datetime	NULL DEFAULT current_timestamp,
	`fr_report`	int	NULL,
	`fr_fs_num`	int	NOT NULL,
	`fr_me_num`	int	NOT NULL
);

CREATE TABLE `WalkMatePost` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`wm_date`	datetime	NULL,
	`wm_time`	varchar(255)	NULL,
	`wm_wms_state`	varchar(50)	NOT NULL
);

CREATE TABLE `Recommend` (
	`re_num`	int primary key auto_increment	NOT NULL,
	`re_state`	int	NULL,
	`re_me_num`	int	NOT NULL,
	`re_po_num`	int	NOT NULL
);

CREATE TABLE `GiveAndTakePost` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`gat_gatt_type`	varchar(255)	NOT NULL,
	`gat_startDate`	datetime	NULL default current_timestamp,
	`gat_endDate`	datetime	NULL default current_timestamp,
	`gat_gat`	varchar(1)	NULL,
	`gat_gats_state`	varchar(50)	NOT NULL,
	`gat_emd_num`	int	NOT NULL
);

CREATE TABLE `Message` (
	`mes_num`	int primary key auto_increment	NOT NULL,
	`mes_content`	longtext	NULL,
	`mes_date`	datetime	NULL,
	`mes_me_receiverNum`	int	NOT NULL,
	`mes_me_senderNum`	int	NOT NULL,
	`mes_readingCheck`	varchar(1)	NULL
);

CREATE TABLE `Report` (
	`rp_num`	int primary key auto_increment	NOT NULL,
	`rp_cause`	varchar(255)	NULL,
	`rp_date`	datetime	NULL,
	`rp_me_num`	int NOT NULL,
	`rp_rt_type`	varchar(50)	NOT NULL,
	`rp_rtt_type`	varchar(50)	NOT NULL,
	`rp_rtt_num`	int	NULL
);

CREATE TABLE `ReportType` (
	`rt_type`	varchar(50)	primary key NOT NULL
);

CREATE TABLE `Animal` (
	`ani_num`	varchar(20) primary key	NOT NULL,
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

CREATE TABLE `AnimalType` (
	`at_type`	varchar(50) primary key	NOT NULL
);

CREATE TABLE `GoodsType` (
	`gt_type`	varchar(50)	NOT NULL
);

CREATE TABLE `Post` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`po_title`	varchar(50)	NULL,
	`po_content`	longtext	NULL,
	`po_date`	datetime	NULL default current_timestamp,
	`po_hidden`	varchar(1)	NULL,
	`po_viewCount`	int	NULL,
	`po_recommendCount`	int	NULL,
	`po_reportCount`	int	NULL,
	`po_notice`	varchar(1)	NULL,
	`po_me_num`	int	NOT NULL
);

CREATE TABLE `MarketPost` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`mp_gts_state`	varchar(50)	NOT NULL,
	`mp_name`	varchar(50)	NULL,
	`mp_content`	varchar(500)	NULL,
	`mp_price`	int	NULL,
	`gt_type`	varchar(50)	NOT NULL
);

CREATE TABLE `GiveAndTakeType` (
	`gatt_type`	varchar(255)	NOT NULL
);

CREATE TABLE `WalkMateMember` (
	`wmm_num`	int primary key auto_increment	NOT NULL,
	`wmm_approve`	varchar(1)	NULL,
	`wmm_po_num`	int	NOT NULL,
	`wmm_ani_num`	varchar(20)	NOT NULL,
	`wmm_me_num`	int	NOT NULL
);

CREATE TABLE `Dibs` (
	`di_num`	int primary key auto_increment	NOT NULL,
	`di_me_num`	int	NOT NULL,
	`di_po_num`	int	NOT NULL
);

CREATE TABLE `ReportTargetType` (
	`rtt_type`	varchar(50)	primary key NOT NULL
);

CREATE TABLE `FacilityShareType` (
	`fst_type`	varchar(50)	primary key NOT NULL
);

CREATE TABLE `MemberStatus` (
	`ms_status`	varchar(50)	primary key NOT NULL
);

CREATE TABLE `ContentsReview` (
	`cr_num`	int primary key auto_increment	NOT NULL,
	`cr_content`	longtext	NULL,
	`cr_grade`	int	NULL,
	`cr_date`	datetime	NULL default current_timestamp,
	`cr_report`	int	NULL,
	`cr_me_num`	int	NOT NULL,
	`po_num`	int	NOT NULL
);

CREATE TABLE `GoodsTradeState` (
	`gts_state`	varchar(50)	NOT NULL
);

CREATE TABLE `ReportPenaltyStandard` (
	`rps_standard`	varchar(50)	primary key NOT NULL,
	`rps_period`	datetime	NULL,
	`rps_number`	int	NULL
);

CREATE TABLE `FriendList` (
	`Key`	VARCHAR(255)	primary key auto_increment NULL,
	`me_num`	int	NOT NULL,
	`me_num2`	int	NOT NULL
);

CREATE TABLE `BlackList` (
	`Key`	VARCHAR(255)	primary key auto_increment NOT NULL,
	`me_num`	int	NOT NULL,
	`me_num2`	int	NOT NULL
);

CREATE TABLE `Bookmark` (
	`bm_num`	int primary key auto_increment	NOT NULL,
	`me_num`	int	NOT NULL,
	`po_num`	int	NOT NULL
);

CREATE TABLE `WalkMateState` (
	`wms_state`	varchar(50)	NOT NULL
);

CREATE TABLE `GiveAndTakeState` (
	`gats_state`	varchar(50)	NOT NULL
);

CREATE TABLE `Emd_areas` (
	`emd_num`	int primary key auto_increment	NOT NULL,
	`emd_sigg_num`	int	NOT NULL,
	`emd_areas`	varchar(2)	NULL,
	`emd_name`	varchar(50)	NULL,
	`emd_version`	varchar(20)	NULL
);

CREATE TABLE `Sido_areas` (
	`sido_num`	int primary key auto_increment	NOT NULL,
	`sido_code`	varchar(2)	NULL,
	`sido_name`	varchar(50)	NULL,
	`sido_version`	varchar(20)	NULL
);

CREATE TABLE `Sigg_areas` (
	`sigg_num`	int primary key auto_increment	NOT NULL,
	`sigg_sido_num`	int	NOT NULL,
	`sigg_code`	varchar(2)	NULL,
	`sigg_name`	varchar(50)	NULL,
	`sigg_version`	varchar(20)	NULL
);

-- CREATE TABLE `Untitled2` (

-- );

CREATE TABLE `WalkMatePet` (
	`wmp_num`	int primary key auto_increment	NOT NULL,
	`ani_num`	varchar(20) NOT NULL
);