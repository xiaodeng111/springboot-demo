package com.demo.clockin.common.lang;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author wangjiawei
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static String EMPTY = " ";
		
	/**
	 * 金额转换
	 * @param money
	 * @return
	 */
	public static String moneyToString(Integer money) {
		if(null != money) {
			Integer inte = money/100;
			Integer dec = money%100;
			StringBuffer sb = new StringBuffer();
			sb.append(inte);
			sb.append(".");
			sb.append(dec < 10 ? "0"+dec : dec);
			return sb.toString();
		} else {
			return "0.00";
		}
	}
	
	/**
	 * 字符串金额转换为整型（乘以100）
	 * @param money
	 * @return Integer
	 * @author: Bobbie.Qi
	 * @time:2017年2月4日
	 */
	public static Integer moneyStrToInt(String money) {
		if(null != money && !"".equals(money)) {
			if(money.contains(".")) {
				return Integer.valueOf(money.replace(".", ""));
			} else {
				return Integer.valueOf(money);
			}
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(moneyStrToInt("23.00"));
	}
	
	public static String lowerFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	public static String upperFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
		return abbr(replaceHtml(str), length);
	}

	/**
	 * 缩略字符串（替换@）
	 * 
	 * @param str
	 *            目标字符串
	 * @return
	 */
	public static String replaceat(String str) {
		if (str == null) {
			return "";
		}
		String s = str.replaceAll("@", " ");
		return s;
	}

	/**
	 * 缩略字符串（替换@）
	 * 
	 * @param str
	 *            目标字符串
	 * @return
	 */
	public static String replaceN(String str) {
		if (str == null) {
			return "";
		}
		String s = str.replaceAll("\n", "<br>");
		return s;
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		// if (val == null)
		// return null;
		return toLong(val).intValue();
	}

	public static Integer toNullableInteger(Object val) {
		if (val == null)
			return null;
		return toLong(val).intValue();
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 获得单词数量
	 * 
	 * @param wordContent
	 * @return
	 */
	public static int getWordCount(String wordContent) {
		int count = 0;
		if (wordContent == null || wordContent.length() == 0) { // 判断是否为null,判断是否为空,如果为null直接返回0
			count = 0;
		} else { // 判断获取字数
			wordContent = wordContent.replaceAll("\"", "");// 去双引号
			wordContent = wordContent.replaceAll(" +", " "); // 清空空格
			wordContent = wordContent.replaceAll(" +", " ");// 去不间断空格
			wordContent = wordContent.trim();
			// 临时变量
			String s4 = "";
			String s3 = "";
			String s1 = "";
			boolean bb = false;
			if (wordContent.length() > 0) {
				s4 = String.valueOf(wordContent.charAt(wordContent.length() - 1));
			}
			for (int i = 0; i < wordContent.length(); i++) {
				s3 = String.valueOf(wordContent.charAt(i));
				int num = s3.getBytes().length;
				if (s3.hashCode() == 32 || s3.hashCode() == 44 || s3.hashCode() == 46 || s3.hashCode() == 59
						|| s3.hashCode() == 63 || s3.hashCode() == 33 || s3.getBytes().length == 2) {
					bb = true;
				}
				if (num == 2) {
					count++;
				} else {
					if (i + 1 < wordContent.length() && (i >= 0)) {
						s1 = String.valueOf(wordContent.charAt(i + 1));
						if ((s1.hashCode() == 32 || s1.hashCode() == 44 || s1.hashCode() == 46 || s1.hashCode() == 59
								|| s1.hashCode() == 63 || s1.hashCode() == 33 || s1.getBytes().length == 2)
								&& (s3.hashCode() != 32 && s3.hashCode() != 44 && s3.hashCode() != 46
										&& s3.hashCode() != 59 && s3.hashCode() != 63 && s3.hashCode() != 33)) {
							count++;
						}
					}
				}
			}
			if (!bb) {
				count++;
			} else {
				if (s4.getBytes().length == 1 && s4.hashCode() != 32 && s4.hashCode() != 44 && s4.hashCode() != 46
						&& s4.hashCode() != 59 && s4.hashCode() != 63 && s4.hashCode() != 33) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 验证手机号码合法性
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobile) {
		
		if (mobile == null)
			return false;

        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");

		Matcher m = p.matcher(mobile);

		return m.matches();

	}
		
	/**
	 * 隐藏手机号码中间4位数字。
	 * @param phone
	 * @return
	 */
	public static String hidePhoneNO(String mobile) {
		if(null != mobile && isMobileNO(mobile)) {
			return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		} else {
			return mobile;
		}
	}

}
