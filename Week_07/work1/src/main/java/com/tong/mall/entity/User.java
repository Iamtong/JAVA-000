package com.tong.mall.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
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
