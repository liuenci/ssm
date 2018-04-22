package com.lean.ssm.chapter2.anno;

import java.util.Date;
@Table(value = "pro")
public class Product {
    @Column(value = "p_id")
    private int pid;
    private String name;
    private Double price;
    @Column(value = "birth")
    private Date birthday;

    public Product() {
    }

    public Product(int pid, String name, Double price, Date birthday) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.birthday = birthday;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
