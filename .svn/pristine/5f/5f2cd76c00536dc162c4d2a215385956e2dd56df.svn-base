set names utf8;

create database if not exists wxwork_db;

USE wxwork_db;

CREATE TABLE `t_app_info` (
  `Fapp_id` varchar(32) NOT NULL COMMENT '应用编号',
  `Fapp_name` varchar(128) NOT NULL COMMENT '应用名称',
  `Fapp_url` varchar(255) NOT NULL COMMENT '应用首页url',
  `Fapp_square_logo` varchar(255) NOT NULL COMMENT '应用方形图标',
  `Fapp_round_logo` varchar(255) NOT NULL COMMENT '应用圆形图标',
  `Fapp_desc` varchar(255) NOT NULL COMMENT '应用功能介绍',
  `Fmch_id` varchar(32) NOT NULL COMMENT '应用提供方的商户号',
  `Ftype` int(8) NOT NULL COMMENT '应用类型：1：个人金融服务应用 2：企业金融服务应用',
  `Fbusiness_style` int(8) NOT NULL COMMENT '应用业务类型（待定义）：1001：业务办理类 1002：信息查询类 1003：开卡 2001：企业开户',
  `Fallow_party` varchar(255) NOT NULL DEFAULT 'ALL' COMMENT '应用可见范围(部门)，默认全部部门',
  `Fallow_tag` varchar(255) NOT NULL DEFAULT 'ALL' COMMENT '应用可见范围(标签) ，默认全部标签',
  `Fallow_user` varchar(255) NOT NULL DEFAULT 'ALL' COMMENT '应用可见范围(用户) ，默认全部用户',
  `Fstate` int(8) NOT NULL COMMENT '应用状态：1：创建 2：测试 3：上架 4：下架',
  `Fapproved_state` int(8) NOT NULL COMMENT '审批状态:1：创建 2：待审批 3：审批通过 4：审批不通过',
  `Frefuse_desc` varchar(255) DEFAULT NULL COMMENT '审批驳回原因',
  `Fapp_scret` varchar(255) NOT NULL COMMENT '应用密钥（需加密存储）',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`Fapp_id`),
  KEY `idx_mch_id` (`Fmch_id`) USING BTREE,
  KEY `idx_modify_time` (`Fmodify_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用信息表';

CREATE TABLE `t_mch_info` (
  `Fmch_id` varchar(32) NOT NULL COMMENT '商户号（金融机构标识）',
  `Fmch_name` varchar(128) NOT NULL COMMENT '商户名称',
  `Fservice_phone` varchar(16) NOT NULL COMMENT '商户客服电话',
  `Fmch_developer` varchar(64) NOT NULL COMMENT '商户接入的bd负责人',
  `Fwxsp_id` varchar(16) NOT NULL COMMENT '商户自己的微信商户号',
  `Fcontact` varchar(64) NOT NULL COMMENT '商户联系人',
  `Fconact_phone` varchar(16) NOT NULL COMMENT '商户联系人手机号码',
  `Fconact_mail` varchar(64) NOT NULL COMMENT '商户联系人邮箱',
  `Ftype` int(8) NOT NULL COMMENT '商户类型：1：银行',
  `Fmch_status` int(8) NOT NULL COMMENT '商户状态:1:可用 2:锁定 3:关闭',
  `Fsing_type` varchar(16) NOT NULL COMMENT '商户签名算法：SHA256',
  `Fsign_key` varchar(255) NOT NULL COMMENT '商户签名密钥',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`Fmch_id`),
  KEY `idx_modify_time` (`Fmodify_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='金融机构信息表';

CREATE TABLE `t_corp_info` (
  `Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
  `Fcorp_name` varchar(128) DEFAULT NULL COMMENT '企业的企业微信名称',
  `Fcorp_full_name` varchar(128) DEFAULT NULL COMMENT '企业主体全称',
  `Fcorp_status` int(8) DEFAULT NULL COMMENT '企业注册状态：1：注册未授权2：注册且已授权（具体授权应用在企业授权表记录）',
  `Fcert_id` varchar(32) DEFAULT '',
  `Fcorp_type` varchar(32) DEFAULT NULL COMMENT '企业企业微信帐号类型：认证号：verified,注册号：unverified',
  `Fsubject_type` int(8) DEFAULT NULL COMMENT '企业类型：1:企业; 2:政府以及事业单位; 3:其他组织；4:团队号',
  `Fcorp_square_logo_url` varchar(255) DEFAULT NULL COMMENT '企业头像地址',
  `Fcorp_user_max` int(8) DEFAULT NULL COMMENT '企业规模',
  `Fapproval_key` varchar(64) DEFAULT '',
  `Fverified_end_time` varchar(32) DEFAULT NULL COMMENT '企业认证到期时间',
  `Fcorp_wxqrcode` varchar(255) DEFAULT NULL COMMENT '企业微信二维码',
  `Fsrc_mch_id` varchar(32) DEFAULT NULL COMMENT '企业来源商户',
  `Fsrc_app_id` varchar(32) DEFAULT NULL COMMENT '企业来源第三方应用 suite id',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`Fcorpid`),
  KEY `idx_modify_time` (`Fmodify_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息表';

CREATE TABLE `t_user_info` (
  `Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
  `Fuserid` varchar(64) NOT NULL COMMENT '用户id',
  `Fname` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `Fdepartment` varchar(128) DEFAULT NULL COMMENT '所属部门',
  `Fposition` varchar(128) DEFAULT NULL COMMENT '职位',
  `Fmobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `Fgender` int(8) DEFAULT NULL COMMENT '性别0：未定义 1：男性 2：女性',
  `Femail` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `Favatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `Flast_logintime` datetime DEFAULT NULL COMMENT '用户最后访问时间',
  `Flast_plat_appid` varchar(32) DEFAULT NULL COMMENT '最后一次访问的平台应用编号',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`Fuserid`,`Fcorpid`) USING BTREE,
  KEY `idx_modify_time` (`Fmodify_time`) USING BTREE,
  KEY `idx_last_logintime` (`Flast_logintime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `t_corp_authen` (
  `Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
  `Fplat_app_id` varchar(32) NOT NULL COMMENT '平台应用编号 suite id: 个人金融服务,企业金融服务',
  `Fauth_status` int(8) NOT NULL COMMENT '企业授权状态1：未授权2：已授权3：解除授权',
  `Fpermanent_code` varchar(255) NOT NULL COMMENT '企业永久授权码（需加密存储）',
  `Fauth_ok_time` datetime DEFAULT NULL COMMENT '企业授权成功时间',
  `Fapp_level` int(8) NOT NULL COMMENT '权限等级。1:通讯录基本信息只读 2:通讯录全部信息只读 3:通讯录全部信息读写 4:单个基本信息只读 5:通讯录全部信息只写',
  `Fallow_party` varchar(255) NOT NULL COMMENT '应用可见范围(部门) ',
  `Fallow_tag` varchar(255) DEFAULT NULL COMMENT '应用可见范围(标签) ',
  `Fallow_user` varchar(255) NOT NULL COMMENT '应用可见范围(用户) ',
  `Fextra_party` varchar(255) NOT NULL COMMENT '额外通讯录（部门）',
  `Fextra_tag` varchar(255) DEFAULT NULL COMMENT '额外通讯录（标签）',
  `Fextra_user` varchar(255) NOT NULL COMMENT '额外通讯录（用户）',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`Fcorpid`,`Fplat_app_id`),
  KEY `idx_modify_time` (`Fmodify_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业授权表';

CREATE TABLE `t_corp_app_relation` (
  `Fcorpid` varchar(64) NOT NULL COMMENT '企业id',
  `Fapp_id` varchar(32) NOT NULL COMMENT '应用编号',
  `Fbank_type` smallint(6) DEFAULT '0',
  `Fop_userid` varchar(512) DEFAULT '',
  `Fbank_uin` varchar(32) DEFAULT '',
  `Fop_phone` varchar(32) DEFAULT '',
  `Fauthen_status` smallint(6) DEFAULT '0',
  `Fauthen_id` varchar(32) DEFAULT '',
  `Fauthen_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `Ftype` int(8) NOT NULL COMMENT '平台应用编号（共两个）个人金融服务,企业金融服务',
  `Fstatus` smallint(16) DEFAULT '2',
  `Finstall_time` datetime DEFAULT NULL COMMENT '应用安装时间',
  `Fcreate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Fmodify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`Fcorpid`,`Fapp_id`),
  KEY `idx_modify_time` (`Fmodify_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业应用关系表';