package com.demo.clockin.common.lang;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final TimeUnit TIME_UNIT = TimeUnit.MILLISECOND;
	/**
	 * 连续格式
	 */
	public static final String DATE_TIME_CONTINUOUS_FORM = "yyyyMMddHHmmssSSS";
	/**
	 * 日期连续格式
	 */
	public static final String DATE_CONTINUOUS_FORM = "yyyyMMdd";
	/**
	 * 精确到日期
	 */
	public static final String DATE_FORM = "yyyy-MM-dd";
	
	/**
	 * 标准时间格式：精准到十分秒
	 */
	public static final String DATE_FORM_DEFALTE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 标准时间格式：精准到分
	 */
	public static final String DATE_FORM_HALF_DEFALTE = "yyyy-MM-dd HH:mm";
	
	private static final String DATE_REG = "\\d{4}-\\d{2}-\\d{2}";
	private static final String TIME_REG = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";

	public enum TimeUnit {
		SECOND(1000), MILLISECOND(1);

		private int value = 0;

		private TimeUnit(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}
	}
	
	public enum FormatPattern {
		FULL("yyyy-MM-dd HH:mm:ss"), DATE("yyyy-MM-dd"), TIME("HH:mm:ss");
		
		private String value;
		
		private FormatPattern(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}

	public static final SimpleDateFormat FULL_FORMATER;
	public static final SimpleDateFormat DATE_FORMATER;
	public static final SimpleDateFormat TIME_FORMATER;

	static {
		FULL_FORMATER = new SimpleDateFormat(FormatPattern.FULL.toString());
		DATE_FORMATER = new SimpleDateFormat(FormatPattern.DATE.toString());
		TIME_FORMATER = new SimpleDateFormat(FormatPattern.TIME.toString());
	}
	
	public static Date parse(String timeStr) throws ParseException {
		return FULL_FORMATER.parse(timeStr);
	}
	
	public static Date parse(String timeStr, SimpleDateFormat dateFormat) throws ParseException {
		return dateFormat.parse(timeStr);
	}
	
	public static long toTimestamp(String timeStr) throws ParseException {
		return toTimestamp(timeStr, TIME_UNIT);
	}
	
	public static long toTimestamp(String timeStr, TimeUnit timeUnit) throws ParseException {
		return parse(timeStr).getTime() / timeUnit.value;
	}
	
	public static long toTimestamp(String timeStr, SimpleDateFormat dateFormat) throws ParseException {
		return toTimestamp(timeStr, dateFormat, TIME_UNIT);
	}
	
	public static long toTimestamp(String timeStr, SimpleDateFormat dateFormat, TimeUnit timeUnit) throws ParseException {
		return parse(timeStr, dateFormat).getTime() / timeUnit.value;
	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return long 
	 * @date 2015年7月21日 上午9:32:05
	 */
	public static long now() {
		return System.currentTimeMillis() / TIME_UNIT.value;
	}
	
	public static long now(TimeUnit timeUnit) {
		return System.currentTimeMillis() / timeUnit.value;
	}
	
	/**
	 * 获取当天的0:0:0
	 * 
	 * @param
	 * @return long 
	 * @date 2015年7月21日 上午9:36:32
	 */
	public static long beginTime() {
		return beginTime(TIME_UNIT);
	}
	
	public static long beginTime(TimeUnit timeUnit) {
		return beginTime(new Date(), timeUnit);
	}
	
	/**
	 * 获取输入时间当天的0:0:0
	 * 
	 * @param date
	 * @return long 
	 * @date 2015年7月21日 上午9:36:32
	 */
	public static long beginTime(Date date) {
		return beginTime(date, TIME_UNIT);
	} 
	
	public static long beginTime(Date date, TimeUnit timeUnit) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (DateUtils.truncate(cal.getTime(), Calendar.DATE).getTime()) / timeUnit.value;
	} 
	
	/**
	 * 获取当天的23:59:59
	 * 
	 * @return long 
	 * @date 2015年7月21日 上午9:30:50
	 */
	public static long endTime() {
		return endTime(new Date());
	}
	
	/**
	 * 获取输入时间当天的23:59:59
	 * 
	 * @param date
	 * @return long 
	 * @date 2015年7月21日 上午9:30:50
	 */
	public static long endTime(Date date) {
		return endTime(date, TIME_UNIT);
	}
	
	public static long endTime(Date date, TimeUnit timeUnit) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return (DateUtils.truncate(cal.getTime(), Calendar.DATE).getTime() - 1) / timeUnit.value;
	}
	
	public static long endTime(String timeStr, SimpleDateFormat dateFormat) throws ParseException {
		return endTime(parse(timeStr, dateFormat));
	}
	
	public static long endTime(String timeStr, SimpleDateFormat dateFormat, TimeUnit timeUnit) throws ParseException {
		return endTime(parse(timeStr, dateFormat), timeUnit);
	}
	
	/**
	 * 把Date转成Timestamp
	 * 
	 * @param date
	 * @return long 
	 * @date 2015年7月21日 上午9:33:15
	 */
	public static long toTimestamp(Date date) {
		return toTimestamp(date, TIME_UNIT);
	}
	
	public static long toTimestamp(Date date, TimeUnit timeUnit) {
		return date.getTime() / timeUnit.value;
	}
	
	public static Date toTimeDate(Long time) {
	     return new Date(time);
	}
	
	/**
	 * 功能描述：返回年
	 * @param date 日期
	 * @return String 返回年
	 */
	public static String getYear(Date date) {
		return DATE_FORMATER.format(date).substring(0, 4);
	}
	
	/**
	 * 功能描述：返回月
	 *
	 * @param date 日期
	 * @return int 返回月
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 功能描述：返回日
	 *
	 * @param date 日期
	 * @return 返回日
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小小时
	 *
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 *
	 * @param date 日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒
	 *
	 * @param date 日期
	 * @return 返回秒
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 *
	 * @param date 日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.getTimeInMillis();
	}
	
	public static String getYearMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}
	
	public static String getYearMonthDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return sdf.format(new Date());
	}

	public static String getYearMonthDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(new Date());
	}

	public static String getDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getTime(){
		return FULL_FORMATER.format(new Date());
	}
	/**
     * 自己转换时间转换为  860 -->10:20
     * @param date
     */
	public static String  getTime(Integer date){
		String timeStr="";
		int hour =date/60;
		int min = date%60;
		if(hour<10){
			timeStr+="0"+hour;
		}else{
			timeStr+=hour;
		}
		timeStr+=":";
		if(min<10){
			timeStr+="0"+min;
		}else{
			timeStr+=min;
		}
		return timeStr;
	}
	public static Date getNowDateTime() {
		return new Date();
	}
	/**
     * 时间戳
     * @return
     */
    public static String timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    /**
	 * 获取当前时间字符串
	 * 
	 * @param form
	 * @return
	 */
	public static String getNowDateString(String form) {
		String nowDate = null;

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(form);
		nowDate = format.format(time.getTime());

		return nowDate;
	}
	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (date != null) {
			String str = null;
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			str = sdf.format(date);
			return str;
		} else {
			return null;
		}
	}
	/**
	 * 字符串转时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date) {
		try {
			if (date.matches(DATE_REG)) {
				SimpleDateFormat sf = new SimpleDateFormat(DATE_FORM);
				return sf.parse(date);
			} else if (date.matches(TIME_REG)) {
				SimpleDateFormat sf = new SimpleDateFormat(FormatPattern.FULL.toString());
				return sf.parse(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}
	/**
	 * 字符串转日期
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String str, String format) {
		if (StringUtils.isNotBlank(str)) {
			//DateTime dt = DateTimeFormat.forPattern(format).parseDateTime(str);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}


}
