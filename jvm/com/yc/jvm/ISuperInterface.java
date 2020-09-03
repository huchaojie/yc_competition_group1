package com.yc.jvm;

public interface ISuperInterface {
	default void show() { // 用default修饰，必须对这个方法做实现，子类中不强制要求覆盖
		System.out.println("哈哈，show一下...");
	}
}
