package com.lean.ssm.chapter2.reflect;

public class ReflectServiceImpl2 {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReflectServiceImpl2(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public ReflectServiceImpl2() {
    }

    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
