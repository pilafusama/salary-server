package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OcrIdCardBackResponse extends DeepseaResponse {

    public static class Data {
        private String authority;

        private String valid_date;

        private int[] authority_confidence_all;

        private int[] valid_date_confidence_all;

        private Date valid_date_start;

        private Date valid_date_end;

        public Date getValid_date_end()
        {
            if (valid_date_end == null && valid_date != null) {
                int index = valid_date.indexOf('-');
                if(-1 != index) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                        valid_date_end = format.parse(valid_date.substring(index + 1));
                    } catch(Exception e) {
                        throw new BizException(BizError.DATE_FORMAT_ERROR.errorCode(),"date format of valid date end");
                    }
                }
            }

            return valid_date_end;
        }

        public Date getValid_date_start()
        {
            if (valid_date_start == null && valid_date != null) {
                int index = valid_date.indexOf('-');
                if(0 < index) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                        valid_date_start = format.parse(valid_date.substring(0, index));
                    } catch(Exception e) {
                        throw new BizException(BizError.DATE_FORMAT_ERROR.errorCode(),"date format of valid date start");
                    }
                }
            }

            return valid_date_start;
        }

        @Override
        public String toString() {
            return String.format("authority: %s, valid date: %s", authority, valid_date);
        }

        // getters and setters

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}

		public String getValid_date() {
			return valid_date;
		}

		public void setValid_date(String valid_date) {
			this.valid_date = valid_date;
		}

		public int[] getAuthority_confidence_all() {
			return authority_confidence_all;
		}

		public void setAuthority_confidence_all(int[] authority_confidence_all) {
			this.authority_confidence_all = authority_confidence_all;
		}

		public int[] getValid_date_confidence_all() {
			return valid_date_confidence_all;
		}

		public void setValid_date_confidence_all(int[] valid_date_confidence_all) {
			this.valid_date_confidence_all = valid_date_confidence_all;
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