package com.tenpay.wxwork.salary.util;

import java.io.FileOutputStream;
import java.io.IOException;

public class BinUtil {

    /**
     * 把字节序列转为十六进制字符串
     *
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(2 * bytes.length);
        for(byte b : bytes)
        {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

    /**
     * 写入二进制数据到文件中，以便调试
     *
     */
    public static void writeBytesToFile(byte[] bytes, String filePath) {
        try (FileOutputStream fileOuputStream = new FileOutputStream(filePath)) {
            System.out.println("write byte[] to file: " + filePath);
            fileOuputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}