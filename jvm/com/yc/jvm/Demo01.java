package com.yc.jvm;

import java.lang.reflect.Method;

/**
 * 使用java.lang.reflect包的方法对类型进行反射调用的时候，如果类型没有进行过初始化，则需要先触发其初始化。
 */
public class Demo01 {
	public void reflect(Class<?> c) throws Exception {
		Method[] methods = c.getDeclaredMethods();
		Object obj = c.newInstance();
		
		for (Method method : methods) {
			method.invoke(obj);  // 调用java.lang.reflect中发Method类
		}
	}
}
