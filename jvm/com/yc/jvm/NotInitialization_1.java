package com.yc.jvm;

/**
 * 非主动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 **/
public class NotInitialization_1 {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
