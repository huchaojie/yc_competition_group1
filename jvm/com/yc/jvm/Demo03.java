package com.yc.jvm;

/**
 * 当一个接口中定义了JDK 8新加入的默认方法（被default关键字修饰的接口方法）时，
 * 如果有这个接口的实现类发生了初始化，那该接口要在其之前被初始化。
 */
@SuppressWarnings("unused")
public class Demo03 implements ISuperInterface{
	public static void main(String[] args) {
		Demo03 sub = new Demo03(); // 必须先初始化接口，因为里面有default方法
	}
}
