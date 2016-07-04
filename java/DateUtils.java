package com.wonders.cmhealth.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 按照给定日期返回该日的起始和结束
	 * @param d
	 * @return
	 */
	public static Date[] getCurrentPareDate(Date d) {
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = df1.format(d);
		String str1 = str+" 00:00:01";
		String str2 = str+" 23:59:59";
		
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = df2.parse(str1);
			d2 = df2.parse(str2);
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		Date[] result = new Date[2];
		result[0] = d1;
		result[1] = d2;
		return result;
	}
	
	/**
	 * 计算两日期间隔日
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compareDayInteval(Date d1,Date d2) {
		int betweenDays = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		boolean flag = false;
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
			flag = true;
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		
		if(flag) {
			betweenDays = -1 * betweenDays;
		}
		return betweenDays;
	}
	
	/**
	 * 在给定的日期上加n天
	 * @param d
	 * @param n
	 * @return
	 */
	public static Date addDay(Date d,int n) {
		long dms = 24l*3600l*1000l;
		dms = ((long)n)*dms;
		long time = d.getTime()+dms;
		Date result = new Date();
		result.setTime(time);
		return result;
	}
	
	/**
	 * 返回两日期中的较大日期
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Date getBig(Date d1,Date d2){
		if(d1== null && d2 == null) {
			return new Date();
		}
		
		if(d1 == null) {
			return d2;
		}
		
		if(d2 == null) {
			return d1;
		}
		if(d2.after(d1)) {
			return d2;
		} else {
			return d1;
		}
	}
	
	/**
	 * 返回两日期中的较小日期
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Date getSmall(Date d1,Date d2){
		if(d1== null && d2 == null) {
			return new Date();
		}
		
		if(d1 == null) {
			return d2;
		}
		
		if(d2 == null) {
			return d1;
		}
		
		if(d2.before(d1)) {
			return d2;
		} else {
			return d1;
		}
		
		
	}
	
	/**
	 * 将日期转化为一天中最晚时刻
	 * @param d
	 * @return
	 */
	public static Date toBegin(Date d) {
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = df1.format(d);
		str = str+" 00:00:01";
		
		Date result = null;
		try {
			result = df2.parse(str);
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 将日期转化为一天中最早时刻
	 * @param d
	 * @return
	 */
	public static Date toEnd(Date d) {
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = df1.format(d);
		str = str+" 23:59:59";
		
		Date result = null;
		try {
			result = df2.parse(str);
		}catch(Throwable t) {
			t.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取指定月的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year,int month) {
		Calendar cal =Calendar.getInstance();//获取当前日期 
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		return cal.getTime();
	}
	
	/**
	 * 获取每个月最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonth(int year,int month){		
		Calendar cal =Calendar.getInstance();//获取当前日期 
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		int day=cal.getActualMaximum(Calendar.DATE);
		return day;	
	}
	
	/**
	 * 按日期生成后1天
	 * @param day
	 * @return
	 */
	public static Date generateNextDay(Date day) {
		Calendar cal = Calendar.getInstance();//获取当前日期 
		cal.setTime(day);
		cal.add(Calendar.DAY_OF_MONTH,1);
		return cal.getTime();
		
	}
	
	/**
	 * 按日期生成前1天
	 * @param day
	 * @return
	 */
	public static Date generateLastDay(Date day) {
		Calendar cal = Calendar.getInstance();//获取当前日期 
		cal.setTime(day);
		cal.add(Calendar.DAY_OF_MONTH,-1);
		return cal.getTime();
		
	}
	
	/**
	 * 获取日期是星期几
	 * @param day
	 * @return 1:星期日  2:星期一  3:星期二  4:星期三  5:星期四  6:星期五  7:星期六
	 */
	public static int getWeekDay(Date day) {
		Calendar cal = Calendar.getInstance();//获取当前日期 
		cal.setTime(day);
		int wd = cal.get(Calendar.DAY_OF_WEEK);
		return wd;
	}
	
	/**根据年月日生成日期
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date generateDay(int year,int month,int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
		
	}
	
	/**
	 * 求出距离给定日期最近的工作日
	 * @param d
	 * @return
	 */
	public static Date getWorkDay(Date d) {
		Date tmp = d;
		int wd = getWeekDay(tmp);
		while(wd == 1 || wd == 7) {
			tmp = addDay(tmp,1);
			wd = getWeekDay(tmp);
		}
		return tmp;
	}
	
	

}
