package com.tenpay.wxwork.salary.model;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class SalaryAccount implements Serializable {

	private static final long serialVersionUID = -5229180694828354681L;

    /**
     * 开户类型
     *
     */
    public enum OpeningType {
        UNKNOWN,
        CREATE,
        REUSE
    }

    public enum CreType {
        ID_CARD
    }

    /**
     * 证件性别
     *
     */
    public enum CreGender {
        UNKNOWN,
        MALE,
        FEMALE
    }

    /**
     * 工资卡与绑定卡关系
     *
     */
    public enum CardBanksRelation {
        SAME_BANK,
        DIFF_BANK
    }

    public enum State {
        INIT(1),                   // 初始状态
		GESTURE_PASSWORD_SET(11),  // 手势密码已设置
        CRE_RECOGNIZED(21),        // 证件已上传并识别

                                   // -- CREATE 新建
        LIVENESS_DETECTED(31),     // 活体检测已完成
        USER_VERIFIED(41),         // 用户身份已核查
        BIND_CARD_RECOGNIZED(51),  // 银行卡号已识别
        OPEN_SMS_SENT(61),         // 新建短信验证码已发送
        OPEN_REQ_SENT(71),         // 新开户请求已发送到银行

                                   // -- REUSE 复用
        REUSE_SMS_SENT(151),       // 复用短信验证码已发送
        REUSE_REQ_SENT(161),       // 复用请求已发送到银行

        ACCOUNT_OPENED(201),       // 开户完成
        ACCOUNT_DELETED(211);      // 已删除（比如离职）

        private int value;

        private State(int value) {
            this.value = value;
        }

        private static final Map<Integer, State> map = new HashMap<Integer, State>();
        static {
            for (State state : State.values()) {
                map.put(state.toInt(), state);
            }
        }

        /**
         * int to State
         */
        public static State fromInt(int value) {
            return map.get(value);
        }

        public int toInt() {
            return value;
        }
    }

    private int salary_account_id;
    private String corpid;                    // '企业id'
    private String userid;                    // '用户id'
    private State state;                        // '开户状态'
    private OpeningType opening_type;        // '开户类型'
    private String salary_card_number;        // '工资卡号（二类户）'
    private String salary_card_bank_type;
    private String salary_card_bank_sname;    // '工资卡发卡银行sname'
    private String salary_card_opened_time;   // '银行的工资卡开户时间戳'
    private String gesture_password;          // '手势密码'
    private String mobile_number;             // '手机号码'
    private CreType cre_type;                // '证件类型'
    private String cre_id;                    // '证件号码'
    private String cre_name;                  // '证件姓名'
    private CreGender cre_gender;            // '证件性别'
    private String cre_nationality;           // '证件民族'
    private String cre_birthdate;              // '证件生日'
    private String cre_address;               // '证件住址'
    private String cre_issue_authority;       // '证件发证机关'
    private String cre_valid_date;            // '证件有效期起始'
    private String cre_expire_date;           // '证件有效期截止'
    private String cre_front_photo;           // '证件正面照'
    private String cre_back_photo;            // '证件背面照'
    private String liveness_detection_number; // '活体检测数字'
    private String liveness_detection_video;  // '活体检测视频'
    private String face_recognition_photo;    // '人脸识别照片'
    private String bind_card_number;          // '绑定的银行卡号（一类户）'
    private String bind_card_bank_sname;      // '绑定银行卡的银行sname'
    private String bind_card_bank_number;     // '绑定银行卡的开户行联行号'
    private String bind_card_photo;           // '银行卡照片'
    private String create_time;               // '创建时间'
    private String modify_time;               // '最后更新时间'
	private CardBanksRelation card_banks_relation;  //工资卡与绑定卡关系
	private String department;//部门


	public int getSalary_account_id() {
		return salary_account_id;
	}
	public void setSalary_account_id(int salary_account_id) {
		this.salary_account_id = salary_account_id;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
    public int getState() {
        return state.toInt();
    }
    public void setState(int state) {
        this.state = State.fromInt(state);
    }
	public OpeningType getOpening_type() {
		return opening_type;
	}
	public void setOpening_type(OpeningType opening_type) {
		this.opening_type = opening_type;
	}
	public String getSalary_card_number() {
		return salary_card_number;
	}
	public void setSalary_card_number(String salary_card_number) {
		this.salary_card_number = salary_card_number;
	}
	public String getSalary_card_bank_sname() {
		return salary_card_bank_sname;
	}
	public void setSalary_card_bank_sname(String salary_card_bank_sname) {
		this.salary_card_bank_sname = salary_card_bank_sname;
	}
	public String getSalary_card_opened_time() {
		return salary_card_opened_time;
	}
	public void setSalary_card_opened_time(String salary_card_opened_time) {
		this.salary_card_opened_time = salary_card_opened_time;
	}
	public String getGesture_password() {
		return gesture_password;
	}
	public void setGesture_password(String gesture_password) {
		this.gesture_password = gesture_password;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public CreType getCre_type() {
		return cre_type;
	}
	public void setCre_type(CreType cre_type) {
		this.cre_type = cre_type;
	}
	public String getCre_id() {
		return cre_id;
	}
	public void setCre_id(String cre_id) {
		this.cre_id = cre_id;
	}
	public String getCre_name() {
		return cre_name;
	}
	public void setCre_name(String cre_name) {
		this.cre_name = cre_name;
	}
	public CreGender getCre_gender() {
		return cre_gender;
	}
	public void setCre_gender(CreGender cre_gender) {
		this.cre_gender = cre_gender;
	}
	public String getCre_nationality() {
		return cre_nationality;
	}
	public void setCre_nationality(String cre_nationality) {
		this.cre_nationality = cre_nationality;
	}
	public String getCre_birthdate() {
		return cre_birthdate;
	}
	public void setCre_birthdate(String cre_birthdate) {
		this.cre_birthdate = cre_birthdate;
	}
	public String getCre_address() {
		return cre_address;
	}
	public void setCre_address(String cre_address) {
		this.cre_address = cre_address;
	}
	public String getCre_issue_authority() {
		return cre_issue_authority;
	}
	public void setCre_issue_authority(String cre_issue_authority) {
		this.cre_issue_authority = cre_issue_authority;
	}
	public String getCre_valid_date() {
		return cre_valid_date;
	}
	public void setCre_valid_date(String cre_valid_date) {
		this.cre_valid_date = cre_valid_date;
	}
	public String getCre_expire_date() {
		return cre_expire_date;
	}
	public void setCre_expire_date(String cre_expire_date) {
		this.cre_expire_date = cre_expire_date;
	}
	public String getLiveness_detection_number() {
		return liveness_detection_number;
	}
	public void setLiveness_detection_number(String liveness_detection_number) {
		this.liveness_detection_number = liveness_detection_number;
	}
	public String getBind_card_number() {
		return bind_card_number;
	}
	public void setBind_card_number(String bind_card_number) {
		this.bind_card_number = bind_card_number;
	}
	public String getBind_card_bank_sname() {
		return bind_card_bank_sname;
	}
	public void setBind_card_bank_sname(String bind_card_bank_sname) {
		this.bind_card_bank_sname = bind_card_bank_sname;
	}
	public String getBind_card_bank_number() {
		return bind_card_bank_number;
	}
	public void setBind_card_bank_number(String bind_card_bank_number) {
		this.bind_card_bank_number = bind_card_bank_number;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
    public String getSalary_card_bank_type() {
        return salary_card_bank_type;
    }

    public void setSalary_card_bank_type(String salary_card_bank_type) {
        this.salary_card_bank_type = salary_card_bank_type;
    }

    public CardBanksRelation getCard_banks_relation() {
        return card_banks_relation;
    }

    public void setCard_banks_relation(CardBanksRelation card_banks_relation) {
        this.card_banks_relation = card_banks_relation;
    }

    public String getCre_front_photo() {
        return cre_front_photo;
    }

    public void setCre_front_photo(String cre_front_photo) {
        this.cre_front_photo = cre_front_photo;
    }

    public String getCre_back_photo() {
        return cre_back_photo;
    }

    public void setCre_back_photo(String cre_back_photo) {
        this.cre_back_photo = cre_back_photo;
    }

    public String getLiveness_detection_video() {
        return liveness_detection_video;
    }

    public void setLiveness_detection_video(String liveness_detection_video) {
        this.liveness_detection_video = liveness_detection_video;
    }

    public String getFace_recognition_photo() {
        return face_recognition_photo;
    }

    public void setFace_recognition_photo(String face_recognition_photo) {
        this.face_recognition_photo = face_recognition_photo;
    }

    public String getBind_card_photo() {
        return bind_card_photo;
    }

    public void setBind_card_photo(String bind_card_photo) {
        this.bind_card_photo = bind_card_photo;
    }

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
