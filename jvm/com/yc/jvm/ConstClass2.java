package com.yc.jvm;

public class ConstClass2 {
    static {
        System.out.println("ConstClass2 init!");
    }

    // 运行时常量
    public static final double HELLOW = Math.random();
}
