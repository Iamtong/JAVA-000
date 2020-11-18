package com.tong.week5.work4;

import java.io.Serializable;

public class Student implements Serializable {
    
    private int id;
    private String name;

    public Student(int i, String name) {
        this.id = i;
        this.name = name;
    }
    public void init(){
        System.out.println("hello...........");
    }
    
    public Student create(){
        return new Student(101,"KK101");
    }
}
