package com.yc.jvm;

/**
 * 当初始化类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
 */
@SuppressWarnings("unused")
public class Demo02  extends SuperClass{
	public static void main(String[] args) {
		Demo02 demo = new Demo02();  // 先初始化父类
	}
}
