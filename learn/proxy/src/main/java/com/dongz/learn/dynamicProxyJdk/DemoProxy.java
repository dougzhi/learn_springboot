package com.dongz.learn.dynamicProxyJdk;

import java.lang.reflect.Proxy;

/**
 * @Auth dz
 * @time 2020/6/24 9:46
 * @desc jdk proxy 缺点， 必须有接口
 */
public class DemoProxy {

    public static <T> T getT(Class<?> clazz, Class<T> interfaces) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{interfaces}, (proxy, method, args) -> {
            System.out.println("method:" + method.getName() + " start");
            Object invoke = method.invoke(clazz.newInstance(), args);
            System.out.println("method:" + method.getName() + " stop");
            return invoke;
        });
    }
}
