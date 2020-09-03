package com.yc.jvm;

/**
 * <clinit>()方法是有编译器自动收集类中的所有类变量的赋值动作和静态语句块中的语句合并产生地方，编译器收集的顺序是有语句在源文件中出现的顺序所决定的
 */
public class Test7_5_3{
	public static Test7_5_3 instance = new Test7_5_3();
	public static int num1;
	public static int num2 = 1;
	
	static {
		num1 ++;
		num2 ++;
	}
	
	public Test7_5_3() {
		num1 ++;
		num2 ++;
	}
	
	public static void main(String[] args) {
		System.out.println("num1 = " + num1); // 2 
		System.out.println("num2 = " + num2); // 2
	}
}
