
insert  into wxwork_db.t_salary_bank_conf (`F_salary_bank_conf_id`,`Fbank_sname`,`Fstate`,`Ftype`,`Fkey`,`Fvalue`,`Fcreate_time`,`Fmodify_time`) values (1,'CCB','AUDITED','CONF','BANK_TYPE','4257',NOW(),NOW()),(2,'CCB','AUDITED','WORKFLOW','OPEN_ACCOUNT_MODE','BIND_AND_OPEN_MODE',NOW(),NOW()),(3,'CCB','AUDITED','WORKFLOW','OPEN_ACCOUNT_ENTRY','/entry/bindAndOpenMode',NOW(),NOW()),(4,'CCB','AUDITED','WORKFLOW','OPEN_ACCOUNT_START_TIME','09:00',NOW(),NOW()),(5,'CCB','AUDITED','WORKFLOW','OPEN_ACCOUNT_END_TIME','17:00',NOW(),NOW()),(6,'CCB','AUDITED','WORKFLOW','IS_LIVENESS_DETECTION_NEEDED','true',NOW(),NOW()),(7,'CCB','AUDITED','GRID','GRID_1_TITLE','建设银行服务',NOW(),NOW()),(8,'CCB','AUDITED','CONF','BANK_NAME','中国建设银行',NOW(),NOW());

insert  into wxwork_db.t_salary_corp_conf (`Fcorpid`,`Fstate`,`Ftype`,`Fkey`,`Fvalue`,`Fcreate_time`,`Fmodify_time`) values ('wwab433cc997fe7ff3','AUDITED','CONF','CORP_TO_BANK','CCB',NOW(), NOW());