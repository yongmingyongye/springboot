-- 抄牌主表
create table t_erp_copybrand(
	copybrand_id NUMBER(19,0) not null primary key, --抄牌主表Id
	notice_number VARCHAR2(20) not null, --销售通知单号
	user_number VARCHAR2(20), --抄牌人
	vehicle_number VARCHAR2(30), --车船号
	status VARCHAR2(10), --状态，表示是否未打印：'0'未打印,'1'已打印,'99'作废
	print_count NUMBER(3,0), --打印次数,默认0次
	create_time DATE --添加时间
);

-- 用于抄牌主表主键自增
create sequence erp_copybrand_seq
start with 1 --从1开始
increment by 1 --表明值每次增长1(步长)
nomaxvalue --设置最大值
nominvalue --设置最小值，start with不能小于最小值
nocycle --是否循环，建议不使用
nocache; --是否启用缓存

-- 抄牌从表
create table t_erp_copybrand_details(
	id NUMBER(19,0) not null primary key, --Id
	copybrand_id NUMBER(19,0) not null, --抄牌主表Id
	notice_number VARCHAR2(20) not null, --销售通知单号
	steelno VARCHAR2(30), --钢铁号
	width NUMBER(19,3), --宽度（直径,规格）
	suttle NUMBER(19,3), --净重
	stoveno VARCHAR2(20), --炉批号
	concode VARCHAR2(200), --合同号（生产号）
	sheaf VARCHAR2(15), --捆号由两位字母加炉批号加3位数字组成
	quantity NUMBER(6,0), --件数
	weight_produce_id NUMBER(19,0) not null, --棒材计量编号,TB_WEIGHT_PRODUCE的Id
	status VARCHAR2(10), --状态，'0'：正常,'99'：作废,默认'0'
	create_time DATE, --创建时间
	create_user VARCHAR2(20) --创建人工号
);

-- 用于抄牌主表主键自增
create sequence erp_copybrandDetails_seq
start with 1 --从1开始
increment by 1 --表明值每次增长1(步长)
nomaxvalue --设置最大值
nominvalue --设置最小值，start with不能小于最小值
nocycle --是否循环，建议不使用
nocache; --是否启用缓存


create table VI_XSCC(
	ID VARCHAR2(20) not null, --通知单号
	HTH VARCHAR2(30) not null, --合同号
	DJH VARCHAR2(10), --吊机号
	DWH VARCHAR2(4), --档位号
	LH VARCHAR2(10), --炉号
	CKH VARCHAR2(50), --仓库号
	CH VARCHAR2(14), --车号
	TZSJ DATE, --通知时间
	BDH VARCHAR2(4), --磅点号
	JLBZ VARCHAR2(2), --计量标记
	PRINTTIMES NUMBER(2,0), --打印次数(0)
	DYRQ DATE, --打印日期
	JLJSRQ DATE, --计量接收日期
	ZL NUMBER(10,6), --重量
	JS NUMBER(6,0), --件数
	FHBY1 NUMBER(10,6), --实发数量
	FHBY2 VARCHAR2(150), --承运日期
	ZT VARCHAR2(20), --发货完成标记(T表示做过出库)
	PERCENT NUMBER(10,6), --化验水份
	NETVALUE NUMBER(10,6), --干基
	gh VARCHAR2(30), --钢号
	DWMC VARCHAR2(150), --单位名称
	DBGSMC VARCHAR2(90), --代办公司名称
	HPSH VARCHAR2(1), --计量通知标记
	CC1 NUMBER(9,2), --长
	CC2 NUMBER(9,2), --宽
	CC3 NUMBER(9,2), --高
	XZ VARCHAR2(20), --形状
	CPYZ VARCHAR2(1), --是否抄牌：1抄牌，0不抄牌
	primary key(ID)
);