package com.yc.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 * 源辰信息
 * @author navy
 * @date 2020年4月18日
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("org.fenixsoft.jvm.chapter7.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.yc.jvm.ClassLoaderTest);
        /*
         * 因为虚拟机存在了两个ClassLoaderTest类，一个是由系统应用程序类加载器加载的，一个是我们自定义的类加载器加载的，虽然都来自同一个Class文件
         * 但依然是两个独立的类，做对象所属类型检查时结果自然为false
         */
    }
}
