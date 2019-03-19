package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OcrIdCardFrontResponse extends DeepseaResponse {

    public static class Data {

        private String address;
        private String nation;
        private String sex;
        private String name;
        private String birth;
        private String id;
        private int[] address_confidence_all;
        private int[] nation_confidence_all;
        private int[] sex_confidence_all;
        private int[] name_confidence_all;
        private int[] birth_confidence_all;
        private int[] id_confidence_all;

        @Override
        public String toString() {
            return String.format("id: %s, name: %s, sex: %s, nation: %s, birth: %s, address: %s",
                                 id, name, sex, nation, birth, address);
        }

		public Date getBirthAsDate() {
            if (birth != null) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    return format.parse(birth);
                } catch(Exception e) {
                    throw new BizException(BizError.DATE_FORMAT_ERROR.errorCode(),"date format of birth date");
                }
            } else {
                return null;
            }
		}

        // generated setters and getters

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getNation() {
			return nation;
		}

		public void setNation(String nation) {
			this.nation = nation;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBirth() {
			return birth;
		}

		public void setBirth(String birth) {
			this.birth = birth;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int[] getAddress_confidence_all() {
			return address_confidence_all;
		}

		public void setAddress_confidence_all(int[] address_confidence_all) {
			this.address_confidence_all = address_confidence_all;
		}

		public int[] getNation_confidence_all() {
			return nation_confidence_all;
		}

		public void setNation_confidence_all(int[] nation_confidence_all) {
			this.nation_confidence_all = nation_confidence_all;
		}

		public int[] getSex_confidence_all() {
			return sex_confidence_all;
		}

		public void setSex_confidence_all(int[] sex_confidence_all) {
			this.sex_confidence_all = sex_confidence_all;
		}

		public int[] getName_confidence_all() {
			return name_confidence_all;
		}

		public void setName_confidence_all(int[] name_confidence_all) {
			this.name_confidence_all = name_confidence_all;
		}

		public int[] getBirth_confidence_all() {
			return birth_confidence_all;
		}

		public void setBirth_confidence_all(int[] birth_confidence_all) {
			this.birth_confidence_all = birth_confidence_all;
		}

		public int[] getId_confidence_all() {
			return id_confidence_all;
		}

		public void setId_confidence_all(int[] id_confidence_all) {
			this.id_confidence_all = id_confidence_all;
		}

    }

    private String filename;

    private Data data;

    @Override
    public String toString() {
        return String.format("data: {%s}, %s, filename: %s", data, super.toString(), filename);
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}