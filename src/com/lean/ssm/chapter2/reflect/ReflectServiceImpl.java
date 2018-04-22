package com.lean.ssm.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectServiceImpl {
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    public Object reflectMethod() throws InvocationTargetException, IllegalAccessException {
	    Object returnObj = null;
	    ReflectServiceImpl target = new ReflectServiceImpl();
        Method method = null;
        try {
            method = ReflectServiceImpl.class.getDeclaredMethod("sayHello", String.class);
            returnObj = method.invoke(target,"张三");
            System.out.println("方法:"+method.invoke(target,"张三"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return returnObj;

    }
}
