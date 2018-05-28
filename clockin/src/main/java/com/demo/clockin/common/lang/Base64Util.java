package com.demo.clockin.common.lang;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author wangwei
 * @date 2018/3/15 0015 17:47.
 */
public class Base64Util {
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();
    public static void main(String[] args) throws Exception {
        final String text = "字串文字";
        String encodedText = encoding(text);
        //编码
        System.out.println(encodedText);
        //解码
        System.out.println(decoding(encodedText));

    }
    /**
     * @Description  编码 base64 用 格式 UTF-8
     * @Author wangwei
     * @Date 2018/3/15 0015 下午 6:30
     * @param
     * @return
     */
    public static String encoding(String str) throws UnsupportedEncodingException {
        return encoding(str, "UTF-8");
    }
    /**
     * @Description  解码 base64 用 格式 UTF-8
     * @Author wangwei
     * @Date 2018/3/15 0015 下午 6:30
     * @param
     * @return
     */
    public static String decoding(String str) throws UnsupportedEncodingException {
        return decoding(str, "UTF-8");
    }
    public static String encoding(String str, String charsetName) throws UnsupportedEncodingException {
        String encodedText = encoder.encodeToString(str.getBytes(charsetName));
        return encodedText;
    }
    public static String decoding(String str, String charsetName) throws UnsupportedEncodingException {
        return new String(decoder.decode(str), charsetName);
    }
}
