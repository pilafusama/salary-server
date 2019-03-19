set names utf8;

create database if not exists wxwork_db;

USE wxwork_db;

-- 对已有表的更改
alter table t_app_info add Fbank_sname varchar(10) not null default '' COMMENT '银行标识' after Ftype;
alter table t_app_info add Fbank_type int not null default 0 COMMENT '银行标识，路由相关' after Fbank_sname;
alter table t_corp_authen add Fagent_id int not null default 0 comment 'suite id 授权给企业产生的' after Fplat_app_id;
alter table t_corp_info add Fcontact_book_synced enum('NOT_SYNCED', 'SYNCED') default 'NOT_SYNCED' COMMENT '通讯录是否已同步' after fsrc_app_id;
alter table t_corp_info add Fcontact_book_synced_time datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '通讯录同步时间' after fcontact_book_synced;
alter table t_user_info add Fstatus tinyint unsigned not null default 4 comment '激活状态: 1=已激活，2=已禁用，4=未激活' after Fname;

alter table t_salary_overview_$$$$$$ add Fbatch_no int(11) not null default 1 comment '此月份第几次发薪' after Fmodify_time;
alter table t_salary_overview_$$$$$$ drop index idx_month_corpid_userid;
alter table t_salary_overview_$$$$$$ add unique index idx_month_corpid_userid_batch (Fmonth,Fcorpid,Fuserid,Fbatch_no)
alter table t_salary_fields add Ftemplate enum('YES', 'NO') default 'YES' COMMENT '是否是模板' after Fremark;
alter table t_salary_account add Fremark varchar(255) DEFAULT NULL COMMENT '备注' after Fbind_card_photo;

-- 工资条应用相关的资源数据
INSERT INTO t_mch_info SET Fmch_id='1001010000000004', Fmch_name='中国建设银行', Fservice_phone='95533', Fwxsp_id='', Fmch_developer='杨达', Fcontact='杨达', Fconact_phone='123', Fconact_mail='yangda.zh@ccb.com', Ftype=1, Fmch_status=1, Fsing_type='SHA-256', Fsign_key='todo', Fcreate_time=NOW(), Fmodify_time=NOW();

INSERT INTO t_app_info SET Fapp_id='10010100000000040310030000000001',
       Fapp_name='中国建设银行总行开户',
        Fapp_url='todo',
Fapp_square_logo='todo',
 Fapp_round_logo='todo',
       Fapp_desc='',
         Fmch_id='1001010000000004',
           Ftype='3',
     Fbank_sname='CCB',
      Fbank_type='4257',
 Fbusiness_style='1003',
    Fallow_party='ALL',
      Fallow_tag='ALL',
     Fallow_user='ALL',
          Fstate='3',
 Fapproved_state='3',
    Frefuse_desc='',
      Fapp_scret='todo',
    Fcreate_time=NOW(),
    Fmodify_time=NOW();

-- 新建工资条相关表
CREATE TABLE `t_salary_account` (
`Fsalary_account_id` int unsigned NOT NULL auto_increment,
`Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
`Fuserid` varchar(64) NOT NULL COMMENT '用户id',
`Fstate` smallint unsigned NOT NULL COMMENT '开户状态',
`Fopening_type` enum('UNKNOWN', 'CREATE', 'REUSE') NOT NULL DEFAULT 'UNKNOWN' COMMENT '开户类型',
`Fsalary_card_number` varchar(128) NOT NULL DEFAULT '' COMMENT '工资卡号（二类户）',
`Fsalary_card_bank_type` int NOT NULL DEFAULT 0 COMMENT '工资卡发卡行 bank_type',
`Fsalary_card_bank_sname` varchar(16) NOT NULL DEFAULT '' COMMENT '工资卡发卡银行sname',
`Fsalary_card_opened_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '银行的工资卡开户时间戳',
`Fcard_banks_relation` enum('SAME_BANK', 'DIFF_BANK') default 'SAME_BANK' COMMENT '工资卡与绑定卡关系',
`Fgesture_password` char(64) NOT NULL DEFAULT '' COMMENT '手势密码',
`Fmobile_number` varchar(64) NOT NULL DEFAULT '' COMMENT '手机号码',
`Fcre_type` enum('ID_CARD') NOT NULL DEFAULT 'ID_CARD' COMMENT '证件类型',
`Fcre_id` varchar(128) NOT NULL DEFAULT '' COMMENT '证件号码',
`Fcre_name` varchar(256) NOT NULL DEFAULT '' COMMENT '证件姓名',
`Fcre_gender` enum('UNKNOWN', 'MALE', 'FEMALE') NOT NULL DEFAULT 'UNKNOWN' COMMENT '证件性别',
`Fcre_nationality` varchar(32) NOT NULL DEFAULT '' COMMENT '证件民族',
`Fcre_birthdate` date NOT NULL DEFAULT '1970-01-01' COMMENT '证件生日',
`Fcre_address` varchar(256) NOT NULL DEFAULT '' COMMENT '证件住址',
`Fcre_issue_authority` varchar(64) NOT NULL DEFAULT '' COMMENT '证件发证机关',
`Fcre_valid_date` date NOT NULL DEFAULT '1970-01-01' COMMENT '证件有效期起始',
`Fcre_expire_date` date NOT NULL DEFAULT '1970-01-01' COMMENT '证件有效期截止',
`Fcre_front_photo` mediumtext COMMENT '证件正面照',
`Fcre_back_photo` mediumtext  COMMENT '证件背面照',
`Fliveness_detection_number` varchar(10) NOT NULL DEFAULT '' COMMENT '活体检测数字',
`Fliveness_detection_video` longtext  COMMENT '活体检测视频',
`Fface_recognition_photo` mediumtext  COMMENT '人脸识别照片',
`Fbind_card_number` varchar(128) NOT NULL DEFAULT '' COMMENT '绑定的银行卡号（一类户）',
`Fbind_card_bank_sname` varchar(16) NOT NULL DEFAULT '' COMMENT '绑定银行卡的银行sname',
`Fbind_card_bank_number` varchar(32) NOT NULL DEFAULT '' COMMENT '绑定银行卡的开户行联行号',
`Fbind_card_photo` mediumtext  COMMENT '银行卡照片',
`Fcreate_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`Fmodify_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后更新时间',
PRIMARY KEY (`Fsalary_account_id`),
UNIQUE idx_corpid_userid (`Fcorpid`, `Fuserid`),
INDEX idx_salary_card_number (`Fsalary_card_number`),
INDEX idx_modify_time (`Fmodify_time`)
) COMMENT='工资条账户表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wxwork_db`.`t_salary_account_fetch` (
`Fsalary_account_fetch_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
`Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
`Fuserid` varchar(64) NOT NULL COMMENT '用户id',
`Fstate` int NOT NULL COMMENT '状态',
`Fbank_fetch_id` varchar(64) NOT NULL COMMENT '银行提现单号',
`Fsalary_card_number` varchar(128) NOT NULL COMMENT '工资账户卡号',
`Fbind_card_number` varchar(128) NOT NULL COMMENT '绑定账户卡号',
`Ffetch_amount` bigint NOT NULL COMMENT '提现金额，单位分',
`Ffetch_type` enum('SAME_BANK', 'DIFF_BANK') NOT NULL COMMENT '提现类型',
`Ffailure_reason` varchar(128) NOT NULL DEFAULT '' COMMENT '失败原因',
`Ffetch_start_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '提现开始时间',
`Ffetch_end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '提现结束时间',
`Fcreate_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
`Fmodify_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后更新时间',
PRIMARY KEY (`Fsalary_account_fetch_id`),
INDEX idx_modify_time (`Fmodify_time`)
) COMMENT='工资条账户提现表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_salary_detail` (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fmonth` varchar(6) NOT NULL COMMENT '工资月份eg.201808',
  `Fcorpid` varchar(128) NOT NULL COMMENT '企业id',
  `Fsequence_number` int(11) NOT NULL COMMENT '同一个企业，同一个月份的发薪序号，从1开始',
  `Fdepartment_name` varchar(256) DEFAULT NULL COMMENT '部门名称',
  `Fuserid` varchar(128) NOT NULL COMMENT '企业微信中的用户id',
  `Fuser_name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `Fcard_number` varchar(128) DEFAULT NULL COMMENT '工资卡',
  `Fcard_bank_number` varchar(16) DEFAULT NULL COMMENT '工资卡的发卡行联行号',
  `Fbank_cards_relation` enum('SAME_BANK','DIFF_BANK') DEFAULT 'SAME_BANK' COMMENT '工资卡与绑定卡关系',
  `Foperator_userid` varchar(128) DEFAULT NULL COMMENT '工资操作人员的userid',
  `Fbefore_tax_salary_sum` bigint(20) DEFAULT '0' COMMENT '税前工资合计',
  `Findividual_income_tax` bigint(20) DEFAULT '0' COMMENT '个人所得税',
  `Factual_salary` bigint(20) DEFAULT '0' COMMENT '实发工资',
  `Fbasic_salary` bigint(20) DEFAULT '0' COMMENT '基本工资',
  `Fperformance_salary` bigint(20) DEFAULT '0' COMMENT '绩效工资',
  `Fovertime_salary` bigint(20) DEFAULT '0' COMMENT '加班工资',
  `Fphone_allowance` bigint(20) DEFAULT '0' COMMENT '话费补贴',
  `Ftransport_allowance` bigint(20) DEFAULT '0' COMMENT '交通补贴',
  `Fmeal_allowance` bigint(20) DEFAULT '0' COMMENT '餐费补贴',
  `Fdeduction` bigint(20) DEFAULT '0' COMMENT '扣款',
  `Fendowment_insurance` bigint(20) DEFAULT '0' COMMENT '养老保险',
  `Fmedical_insurance` bigint(20) DEFAULT '0' COMMENT '医疗保险',
  `Funemployment_insurance` bigint(20) DEFAULT '0' COMMENT '失业保险',
  `Femployment_injury_insurance` bigint(20) DEFAULT '0' COMMENT '工伤保险',
  `Fhousing_fund` bigint(20) DEFAULT '0' COMMENT '住房公积金',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  UNIQUE KEY `idx_corpid_month_sequenceNumber` (`Fmonth`,`Fcorpid`,`Fsequence_number`),
  UNIQUE KEY `idx_corpid_userid_month` (`Fmonth`,`Fcorpid`,`Fuserid`),
  KEY `idx_modify_time` (`Fmodify_time`)
) COMMENT='工资明细表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE wxwork_db.t_corp_department (
  `Fcorp_department_id` int unsigned not null auto_increment COMMENT '自增id',
  `Fcorpid` varchar(64) not null COMMENT '企业 id',
  `Fdepartment_id` int unsigned not null COMMENT '部门 id',
  `Fdepartment_name` varchar(128) not null COMMENT '部门名称',
  `Fparent_department_id` int unsigned not null COMMENT '父部门 id',
  `Forder` int unsigned COMMENT '在父部门中的次序值。order值大的排序靠前',
  `Fcreate_time` Datetime COMMENT '创建时间',
  `Fmodify_time` Datetime COMMENT '最后更新时间',
  PRIMARY KEY (`Fcorp_department_id`),
  UNIQUE KEY `idx_corp_department_id` (`Fcorpid`, `Fdepartment_id`),
  KEY `idx_modify_time` (`Fmodify_time`)
) COMMENT='企业部门表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wxwork_db`.`t_msgnotify` (
  `Fmsgnotify_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Fbusi_type` smallint(6) NOT NULL,
  `Fbusi_id` varchar(512) NOT NULL,
  `Fstate` enum('TO_NOTIFY','SUCCESS','FAIL') NOT NULL,
  `Fretry_count` smallint(6) NOT NULL,
  `Flast_notify_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `Fcreate_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `Fmodify_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`Fmsgnotify_id`),
  KEY `idx_modify_time` (`Fmodify_time`),
  KEY `idx_busiId_busiType` (`Fbusi_type`,`Fbusi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息通知表';

-- 临时过渡
CREATE TABLE t_salary_detail_no1 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fmonth` varchar(6) NOT NULL COMMENT '工资月份eg.201808',
  `Fcorpid` varchar(128) NOT NULL COMMENT '企业id',
  `Fsequence_number` int(11) NOT NULL COMMENT '同一个企业，同一个月份的发薪序号，从1开始',
  `Fdepartment_name` varchar(256) DEFAULT NULL COMMENT '部门名称',
  `Fuserid` varchar(128) NOT NULL COMMENT '企业微信中的用户id',
  `Fuser_name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `Fcard_number` varchar(128) DEFAULT NULL COMMENT '工资卡',
  `Fcard_bank_number` varchar(16) DEFAULT NULL COMMENT '工资卡的发卡行联行号',
  `Fbank_cards_relation` enum('SAME_BANK','DIFF_BANK') DEFAULT 'SAME_BANK' COMMENT '工资卡与绑定卡关系',
  `Foperator_userid` varchar(128) DEFAULT NULL COMMENT '工资操作人员的userid',
  `Fbasic_salary` bigint(20) DEFAULT '0' COMMENT '基本工资',
  `Fpost_salary` bigint(20) DEFAULT '0' COMMENT '岗位补贴',
  `Fovertime_salary` bigint(20) DEFAULT '0' COMMENT '加班费',
  `Fhouse_salary` bigint(20) DEFAULT '0' COMMENT '房屋补贴',
  `Fperformance_salary` bigint(20) DEFAULT '0' COMMENT '绩效全额',
  `Fperformance_grate` varchar(20) DEFAULT '0' COMMENT '绩效得分',
  `Fperformance_actual` bigint(20) DEFAULT '0' COMMENT '绩效实发',
  `Fphone_allowance` bigint(20) DEFAULT '0' COMMENT '通讯补贴',
  `Fother` bigint(20) DEFAULT '0' COMMENT '其他',
  `Fshould_get_salary` bigint(20) DEFAULT '0' COMMENT '应发款合计',
  `Fhouse_rent` bigint(20) DEFAULT '0' COMMENT '房租',
  `Fsocial_person_tax` bigint(20) DEFAULT '0' COMMENT '社保个人缴交',
  `Fshould_tax_money` bigint(20) DEFAULT '0' COMMENT '应纳税所得额',
  `Fperson_tax` bigint(20) DEFAULT '0' COMMENT '个人所得税',
  `Fsick_later` bigint(20) DEFAULT '0' COMMENT '病事假/迟到',
  `Fhousing_fund` bigint(20) DEFAULT '0' COMMENT '公积金',
  `Fother_deduction` bigint(20) DEFAULT '0' COMMENT '其他扣款',
  `Fshould_deduction_salary` bigint(20) DEFAULT '0' COMMENT '应扣款合计',
  `Factual_salary` bigint(20) DEFAULT '0' COMMENT '实发额',
  `Fsure_state` enum('YES','NO') DEFAULT 'NO' COMMENT '确认状态 0：是；1：否',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `Fmark` text COMMENT '备注',
  PRIMARY KEY (`Fsalary_detail_id`),
  UNIQUE KEY `idx_corpid_month_seqno` (`Fmonth`,`Fcorpid`,`Fsequence_number`),
  UNIQUE KEY `idx_corpid_userid_month` (`Fmonth`,`Fcorpid`,`Fuserid`),
  KEY `idx_modify_time` (`Fmodify_time`)
) COMMENT='过渡的薪资明细表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE wxwork_db.t_salary_fields (
  `Fsalary_fields_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fcorpid` varchar(128) NOT NULL COMMENT '企业id',
  `Fsalary_fields` varchar(1024) DEFAULT NULL COMMENT '应发工资字段',
  `Fdeduction_fields` varchar(1024) DEFAULT NULL COMMENT '扣款字段',
  `Fremark` varchar(128) DEFAULT NULL COMMENT '备注',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_fields_id`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资字段表';

CREATE TABLE `t_grid_info` (
  `Fgrid_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fbank_sname` varchar(32) NOT NULL COMMENT '银行英文简称',
  `Fgrid_num` tinyint(4) NOT NULL COMMENT '宫格序号',
  `Fgrid_title` varchar(32) NOT NULL COMMENT '宫格名称',
  `Fgrid_entry` varchar(256) DEFAULT NULL COMMENT '宫格入口',
  `Fgrid_entry_type` varchar(32) DEFAULT NULL COMMENT '宫格入口类型（暂时只有h5）',
  `Fgrid_icon` mediumtext COMMENT '宫格图片类型',
  `Fcorp_tag` varchar(256) DEFAULT NULL COMMENT '企业类型（拼接）',
  `Fuser_tag` varchar(256) DEFAULT NULL COMMENT '用户标签（拼接）',
  `Fstate` enum('USABLE','UNUSABLE') DEFAULT 'USABLE' COMMENT '状态',
  PRIMARY KEY (`Fgrid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='九宫格配置表';



CREATE TABLE wxwork_db.t_promotion_qrcode (
  `Fpromotion_qrcode_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fbank_sname` varchar(16) DEFAULT NULL COMMENT '银行英文缩写',
  `Fbank_name` varchar(32) DEFAULT NULL COMMENT '所属银行',
  `Fstate` enum('USABLE','UNUSABLE') DEFAULT 'USABLE' COMMENT '状态（USABLE-可用；UNUSABLE-不可用）',
  `Fqrcode_name` varchar(32) DEFAULT NULL COMMENT '二维码名称',
  `Ftemplate_id` varchar(32) DEFAULT NULL COMMENT '推广包ID',
  `Fqrcode_logo` mediumtext COMMENT 'logo图标base64编码',
  `Fpromoted_corp_count` int(11) DEFAULT '0' COMMENT '推广企业计数，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fpromotion_qrcode_id`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '推广二维码表';


CREATE TABLE wxwork_db.t_salary_bank_conf (
  `F_salary_bank_conf_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fbank_sname` varchar(8) NOT NULL COMMENT '银行简称，全大写',
  `Fstate` enum('AUDITING','AUDITED','DELETED') NOT NULL COMMENT '状态：AUDITING 审核中；AUDITED 已审核；DELETED 已废弃',
  `Ftype` varchar(16) NOT NULL COMMENT '配置类型',
  `Fkey` varchar(32) DEFAULT NULL COMMENT '定义对应的key',
  `Fvalue` varchar(1024) DEFAULT NULL COMMENT '具体根据 Fkey 对应的内容定义：对于单一值直接保存字符串；对于多维的数据，保存json序列化后的字符串',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`F_salary_bank_conf_id`),
  KEY `idx_bank_sname_type_key_state` (`Fbank_sname`,`Fstate`,`Ftype`,`Fkey`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行配置表';


CREATE TABLE wxwork_db.t_salary_corp_conf (
  `F_salary_corp_conf_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
  `Fstate` enum('AUDITING','AUDITED','DELETED') NOT NULL COMMENT '状态:AUDITING 审核中;AUDITED 已审核;DELETED 已废弃',
  `Ftype` varchar(16) NOT NULL COMMENT '配置类型',
  `Fkey` varchar(32) DEFAULT NULL COMMENT '定义的key',
  `Fvalue` varchar(1024) DEFAULT NULL COMMENT '具体根据 Fkey 对应的内容定义：对于单一值直接保存字符串；对于多维的数据，保存json序列化后的字符串',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`F_salary_corp_conf_id`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业配置表';
