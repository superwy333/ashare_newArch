package com.zynsun.openplatform.util;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PlatformBeanUtils{
//	static{
//		//允许转换bean属性为date的字段为空
//		ConvertUtils.register(new SqlDateConverter(null), java.util.Date.class);
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}

	/**
	 * 复制bean属性
	 * @param target 目标对象
	 * @param source 来源对象
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public  static void copyProperties(Object target, Object source) {

		BeanUtils.copyProperties(source,target);
	}

	/**
	 *
	 * <p>Title: copyProperties</p>
	 * <p>Description:复制 bean属性</p>
	 * @param target 目标class对象
	 * @param source 来源对象
	 * @return 目标对象
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public  static <T> T copyProperties(Class<T> target, Object source)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		T t=target.newInstance();
		copyProperties(t, source);
		return t;
	}

	/**
	 *
	 * <p>Title: copyProperties</p>
	 * <p>Description:复制 bean属性</p>
	 * @param targetClsType 目标class对象
	 * @param sources 来源对象
	 * @return 目标对象
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public  static <T,V> Page<T> copyProperties(Class<T> targetClsType, List<V> sources)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Page<T> t = new Page<T>();
		for (V obj: sources) {
			t.add(copyProperties(targetClsType,obj));
		}
		return t;
	}
}