package com.tong.week5.work4;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.school.starter")
class SpringBootProperties {
    private List<Student> students;
}
