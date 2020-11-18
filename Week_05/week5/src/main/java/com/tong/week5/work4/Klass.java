package com.tong.week5.work4;

import java.util.List;
public class Klass { 
    
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    public void setStudents(List students){
        this.students = students;
    }
    public List getStudents(){
        return this.students;
    }
}
