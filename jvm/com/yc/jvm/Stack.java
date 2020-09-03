package com.yc.jvm;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 源辰信息
 * @author navy
 * @date 2020年4月18日
 */
public class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_SIZE = 16;
	
	public Stack() {
		elements = new Object[DEFAULT_SIZE];
	}
	
	public void push(Object obj) {
		ensureCapacity();
		elements[size] = obj;
	}
	
	/*
	 * 不严格地讲，这段程序有一个"内存泄露"的问题。如果一个栈先是增长(入栈)，然后再收缩(出栈)，那么。从栈中弹出来的对象不会被当做垃圾回收，即使
	 * 使用栈的程序不再引用这个对象，它们也不会被回收。这是因为，栈内部维护着对这些对象的"过期引用"
	 * 调整代码如下：
	 * 	if (size == 0) {
	 * 		throw new EmptyStackException();
	 * 	}
	 *  Object obj = elements[--size];
	 *  elements[size] = null; // 消除数组的过期引用
	 * 	return obj;
	 */
	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}
	
	public void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2*size + 1);
		}
	}
}
