package com.tenpay.wxwork.salary.util;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import ws.schild.jave.*;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

public class VideoCompressUtil {

    private static String TEMP_PATH = System.getProperty("java.io.tmpdir");

    public static String getCompressedBase64String(String base64String) throws BizException {
        String compressedBase64String = null;
        File source = null;
        File target = null;
        try {
            String fileName = TEMP_PATH + File.separator + UUID.randomUUID();
            target = new File(fileName);

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(64000));
            audio.setSamplingRate(new Integer(44100));
            audio.setChannels(new Integer(2));

            VideoAttributes video = new VideoAttributes();
            video.setCodec("mpeg4");
            video.setBitRate(new Integer(64000));
            video.setFrameRate(new Integer(15));

            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setOffset(1.0f);
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);

            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);

            compressedBase64String  = transformFileToBase64(target);

        } catch (IOException e) {
            throw new BizException(BizError.COMPRESS_VIDEO_ERROR);
        }catch (EncoderException e) {
            throw new BizException(BizError.COMPRESS_VIDEO_ERROR);
        }finally {
            if (source != null) source.deleteOnExit();
            if (target != null) target.deleteOnExit();
        }

        return compressedBase64String;
    }

    public static File transformBase64ToFile(String base64) throws IOException {
        byte[] decode = Base64.getDecoder().decode(base64);
        OutputStream out = null;
        File file = null;
        try {
            String fileName = TEMP_PATH + File.separator + UUID.randomUUID();
            file = new File(fileName);
            out = new FileOutputStream(file);
            out.write(decode);
        } finally {
            if(out != null){
                out.flush();
                out.close();
            }
        }

        return file;
    }

    public static String transformFileToBase64(File file) throws IOException {
        byte[] fileBytes = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(file);
            fileBytes = new byte[(int) file.length()];
            fileInputStream.read(fileBytes);
            fileInputStream.close();
        }finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }

        return Base64.getEncoder().encodeToString(fileBytes);
    }
}
