package com.lean.ssm.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class ReflectTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ReflectTest reflectTest = new ReflectTest();
        ReflectServiceImpl reflectServiceImpl = reflectTest.getInstance();
        reflectServiceImpl.sayHello("张三");
        System.out.println(reflectServiceImpl.reflectMethod());

        ReflectServiceImpl2 reflectServiceImpl2 = reflectTest.getInstance2();
        reflectServiceImpl2.sayHello("李四");

        new Date().getTime();
    }

    public ReflectServiceImpl getInstance() {
        ReflectServiceImpl object = null;
        try {
            object = (ReflectServiceImpl) Class.forName("com.lean.ssm.chapter2.reflect.ReflectServiceImpl").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public ReflectServiceImpl2 getInstance2() {
        ReflectServiceImpl2 object = null;
        try {
            object = (ReflectServiceImpl2) Class.forName("com.lean.ssm.chapter2.reflect.ReflectServiceImpl2").getConstructor(int.class, String.class).newInstance(1, "zhangsan");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
