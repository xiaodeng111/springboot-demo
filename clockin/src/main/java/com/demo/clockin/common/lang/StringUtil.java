package com.demo.clockin.common.lang;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public  final static String EMPTY = "";
	private final static String SPLIT_SIGN = "~@&!<./-%#";
	private final static String BASE_RANDOM_STR="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void checkString(String name, String value) {
		if (value == null) {
            throw new NullPointerException(name + " can't be null");
        }
        if ("".equals(value)) {
        	throw new IllegalArgumentException(name + " can't be empty");
        }
	}
 
	public static boolean isEmpty(String... value) {
		if (value == null || value.length <= 0) {
			return true;
		}
		for (String s : value) {
			if (s != null && s.length() > 0) {
				return false;
			}
		}
		return true;
	}
 
	public static boolean isNotEmpty(String... value) {
		if (isEmpty(value)) {
        	return false;
        }else {
			return true;
		}
	}
 
	public static boolean equals(String s1, String s2){
		if (isNotEmpty(s1)) {
			return s1.equals(s2);
		}else if (isNotEmpty(s2)) {
			return s2.equals(s1);
		}else {
			return true;
		}
	}
 
	public static String stackTrace2String(Throwable t) {
		StringWriter sw = new StringWriter(); 
		PrintWriter pw = new PrintWriter(sw); 
		t.printStackTrace(pw); 
		return sw.toString();
	}
	
	public static String[] toStringArray(String str, String delimiters) {
		if (str == null) {
			return null;
		}
		
		if (delimiters == null) {
            throw new NullPointerException("delimiters can't be null");
        }
		return str.split(delimiters);
	}
	
	public static int toInt(String str) {
		if (isEmpty(str)) {
			return 0;
		}
		return Integer.parseInt(str);
	}
	
	public static int countStr(String input, String regex) {
		checkString("input", input);
		checkString("regex", regex);
		int count = 0;
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (regex.equals(String.valueOf(c[i]))) {
				count++;
			}
		}
		return count;
	}
	
	public static String merge(String s1, String s2) {
		return s1 + SPLIT_SIGN + s2;
	}
	
	public static String[] split(String s) {
		return s.split(SPLIT_SIGN);
	}
	/**
	 * 生成对应的位数的字符串包含[0-9 a-z A-Z]的数据
	 */
	public static String randomStr(int bit){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<bit;i++){
			int randomInt = (int) (Math.random()*BASE_RANDOM_STR.length());
			sb.append(BASE_RANDOM_STR.charAt(randomInt));
		}
		return sb.toString();
	}
	/**
	 *生成32的字符串 
	 */
	public static String random32Str(){
		return randomStr(32);
	}

	/**
	 * 字符串MD5加密
	 * @param input
	 * @return
	 */
	public static String getMD5String(String input) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] inputByteArray = null;
			try {
				inputByteArray = input.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			messageDigest.update(inputByteArray);
			byte[] resultByteArray = messageDigest.digest();
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符数组转字符串
	 * @param byteArray
	 * @return
	 */
	public static String byteArrayToHex(byte[] byteArray) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] resultCharArray = new char[byteArray.length * 2];
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultCharArray);
	}

	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return boolean
	 * @author: Bobbie.Qi
	 * @time:2017年6月29日
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
