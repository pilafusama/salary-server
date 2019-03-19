
CREATE TABLE wxwork_db.t_salary_overview_$$$$$$ (
  `Fsalary_overview_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fmonth` varchar(6) NOT NULL COMMENT '工资月份 e.g. 201808',
  `Fcorpid` varchar(128) NOT NULL COMMENT '企业id',
  `Fsequence_number` int(11) DEFAULT NULL COMMENT '同一个企业，同一个月份的发薪序号，从1开始',
  `Fdepartment_name` varchar(256) DEFAULT NULL COMMENT '部门名称',
  `Fuserid` varchar(128) DEFAULT NULL COMMENT '企业微信中的用户id',
  `Fuser_name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `Fcard_number` varchar(64) DEFAULT NULL COMMENT '工资卡',
  `Fcard_bank_number` varchar(16) DEFAULT NULL COMMENT '工资卡的发卡行联行号',
  `Fbank_cards_relation` enum('SAME_BANK','DIFF_BANK') DEFAULT 'SAME_BANK' COMMENT '工资卡与二类户所属银行间关系',
  `Foperator_userid` varchar(128) DEFAULT NULL COMMENT 'Foperator_userid',
  `Factual_salary` bigint(20) DEFAULT '0' COMMENT '实发工资=应发工资总额-扣款总额',
  `Fsalary_sum` bigint(20) DEFAULT '0' COMMENT '应发工资总额',
  `Fdeduction_sum` bigint(20) DEFAULT '0' COMMENT '扣款总额',
  `Fremark` varchar(128) DEFAULT NULL COMMENT '备注',
  `Fis_read` enum('NO','YES') DEFAULT 'NO' COMMENT '员工是否已阅读此工资条',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `Fbatch_no` int(11) DEFAULT '1' COMMENT '此月份发薪次数',
  PRIMARY KEY (`Fsalary_overview_id`),
  UNIQUE KEY `idx_month_corpid_seq` (`Fmonth`,`Fcorpid`,`Fsequence_number`),
  UNIQUE KEY `idx_month_corpid_userid_batch` (`Fmonth`,`Fcorpid`,`Fuserid`,`Fbatch_no`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资总览表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$0 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';


CREATE TABLE wxwork_db.t_salary_detail_$$$$$$1 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$2 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$3 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$4 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$5 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$6 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$7 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$8 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';

CREATE TABLE wxwork_db.t_salary_detail_$$$$$$9 (
  `Fsalary_detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `Fsalary_overview_id` int(11) NOT NULL COMMENT '总览表id',
  `Fcategory` enum('SALARY','DEDUCTION') NOT NULL DEFAULT 'SALARY' COMMENT '类别;SALARY （应发）工资;DEDUCTION 扣款',
  `Fdetail_name` varchar(64) NOT NULL COMMENT '明细项名称，字段名称来自工资字段表',
  `Fdetail_amount` bigint(20) DEFAULT '0' COMMENT '明细项金额，默认为0',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`Fsalary_detail_id`),
  KEY `idx_salary_overview_id` (`Fsalary_overview_id`, `Fcategory`),
  KEY `idx_modify_time` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细表';
