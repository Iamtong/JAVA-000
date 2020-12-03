package com.tong.demo2.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Data
public class User {
    private int id;
    private String userName;
    private String phone;
    private String userPass;
    private String realName;
    private int isLock;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
