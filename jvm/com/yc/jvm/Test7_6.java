package com.yc.jvm;

/**
 * 源辰信息
 * @author navy
 * @date 2020年4月18日
 */
public class Test7_6 {
	static class Parent {
		public static int A = 1;
		
		static {
			A = 2;
		}
	}

	static class Sub extends Parent {
		public static int B = A;
	}
	
	public static void main(String[] args) {
		/*
		 *  由于父类的<Clinit>()方法先执行，也就意味着父类中定义的静态语句块会优于子类的变量赋值操作，所以B会输出2而不是1
		 *  如果一个类中没有静态语句块，也没有对变量的赋值操作，那么编译器可以不为这个类生成<clinit>()方法
		 */
		System.out.println(Sub.B);
	}
}
