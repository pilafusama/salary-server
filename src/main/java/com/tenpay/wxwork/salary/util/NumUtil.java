package com.tenpay.wxwork.salary.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class NumUtil {

    /**
     * 将BiDdecimal类型的数据转为BigInteger类型
     * @param v1
     * @return
     */
    public static BigInteger bigdecimalToBigInteger(BigDecimal v1){
        if(v1 == null || String.valueOf(v1).equals("-")){
            v1 = new BigDecimal(0);
        }
        BigDecimal param = new BigDecimal(Double.toString(v1.doubleValue()));
        BigDecimal param2 = new BigDecimal(Double.toString(100));
        return param.multiply(param2).toBigInteger();
    }

    /**
     * 将String类型的数据转为BigInteger类型
     * @param v1
     * @return
     */
    public static BigInteger stringToBigInteger(String v1){
        if(v1 == null || v1.equals("-") || v1.equals("")){
            v1 = "0";
        }
        BigDecimal param = new BigDecimal(v1);
        BigDecimal param2 = new BigDecimal(Double.toString(100));
        return param.multiply(param2).toBigInteger();
    }

    /**
     * 将BigInteger的金额分为单位转成元为单位
     * @param v1
     * @return
     */
    public static String fenToyuan(String v1){
        if(v1 == null || v1.equals("-") || v1.equals("")){
            v1 = "0";
        }
        String res  = String.valueOf((Double.valueOf(v1) / 100));
        BigDecimal param = new BigDecimal(res);
        DecimalFormat df=new DecimalFormat("###,###.##");
        return df.format(param);
    }

    /**
     * 0 指前面补充零
     * formatLength 字符总长度为 formatLength
     * d 代表为正数。
    　*/
    public static String frontCompWithZero(int sourceData,int formatLength){

        String newString = String.format("%0"+formatLength+"d", sourceData);

        return newString;

    }

}
