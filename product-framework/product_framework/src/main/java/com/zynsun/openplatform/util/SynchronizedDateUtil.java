package com.zynsun.openplatform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyang.li created on 2016 16-2-26 上午11:35
 * @version $Id$
 */
public class SynchronizedDateUtil {
	
	/** 锁对象 */
	private static final Object lockObj = new Object();
	
	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
	
	/**
	 * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getSimpledDateformat(pattern).format(date);
	}
	
	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSimpledDateformat(pattern).parse(dateStr);
	}
	
	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSimpledDateformat(final String pattern) {
		ThreadLocal<SimpleDateFormat> threadLocal = sdfMap.get(pattern);
		
		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (threadLocal == null) {
			synchronized (lockObj) {
				threadLocal = sdfMap.get(pattern);
				if (threadLocal == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
//					System.out.println("put new sdf of pattern " + pattern + " to map");
					// 使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
					threadLocal = new ThreadLocal<SimpleDateFormat>() {
						
						@Override
						protected SimpleDateFormat initialValue() {
//							System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, threadLocal);
				}
			}
		}
		
		return threadLocal.get();
	}
}
