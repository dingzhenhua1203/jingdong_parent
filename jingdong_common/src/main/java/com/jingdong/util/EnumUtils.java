package com.jingdong.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举工具
 *
 * @author Monkey.Sun
 * @create 2019-02-20 18:02
 **/
public class EnumUtils {

	private static final Logger logger = LoggerFactory.getLogger(EnumUtils.class);

	private static <T> Object getMethodValue(String methodName, T obj,
			Object... args) {

		Object resut = "";
		try {
			//获取方法数组，这里只要共有的方法
			Method[] methods = obj.getClass().getMethods();
			if (methods.length <= 0) {
				return resut;
			}
			Method method = null;
			for (int i = 0, len = methods.length; i < len; i++) {
				//忽略大小写取方法
				if (methods[i].getName().equalsIgnoreCase(methodName)) {
					//如果存在，则取出正确的方法名称
					methodName = methods[i].getName();
					method = methods[i];
					break;
				}
			}
			if (method == null) {
				return resut;
			}
			//方法执行
			resut = method.invoke(obj, args);
			if (resut == null) {
				resut = "";
			}
			//返回结果
			return resut;
		} catch (Exception e) {
			logger.error("获取错误信息：{}", e.getMessage());
		}
		return resut;
	}

	public static <T> Map<Object, String> enumToMap(Class<?> enumT, String... methodNames) throws Exception {

		Map<Object, String> enummap = new HashMap<>();
		if (!enumT.isEnum()) {
			return enummap;
		}
		//获取枚举的所有枚举属性
		T[] enums = (T[]) enumT.getEnumConstants();
		if (enums == null || enums.length <= 0) {
			return enummap;
		}
		int count = methodNames.length;
		//默认接口value方法
		String valueMathod = "getValue";
		//默认接口description方法
		String desMathod = "getDescription";
		//扩展方法
		if (count >= 1 && !"".equals(methodNames[0])) {
			valueMathod = methodNames[0];
		}
		if (count == 2 && !"".equals(methodNames[1])) {
			desMathod = methodNames[1];
		}
		for (int i = 0, len = enums.length; i < len; i++) {
			T tobj = enums[i];
			try {
				//获取value值
				Object resultValue = getMethodValue(valueMathod, tobj);
				if ("".equals(resultValue)) {
					continue;
				}
				//获取description描述值
				Object resultDes = getMethodValue(desMathod, tobj);
				if ("".equals(resultDes)) {
					//如果描述不存在获取属性值
					resultDes = tobj;
				}
				enummap.put(resultValue, resultDes + "");
			} catch (Exception e) {
				logger.error("获取错误信息：{}", e.getMessage());
			}
		}
		return enummap;
	}
}
