package com.lean.ssm.chapter2.reflect;

public class ReflectTest {

	public static void main(String[] args) {
		ReflectTest reflectTest = new ReflectTest();
		ReflectServiceImpl reflectServiceImpl = reflectTest.getInstance();
		reflectServiceImpl.sayHello("уехЩ");

	}
	
	public ReflectServiceImpl getInstance() {
		ReflectServiceImpl object = null;
		try {
			object = (ReflectServiceImpl)Class.forName("com.lean.ssm.chapter2.reflect.ReflectServiceImpl").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}

}
