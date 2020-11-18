package com.tong.week5.work4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(SpringBootProperties.class)
@ConditionalOnProperty(prefix = "spring.school.starter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SpringBootConfiguration {
    @Autowired
    SpringBootProperties configuration;

    @Bean
    public Klass klass() {
        List<Student> students = configuration.getStudents();
        Klass klass = new Klass();
        klass.setStudents(students);
        return klass;
    }

    @Bean
    @Autowired
    public School school(Klass klass) {
        School school = new School();
        school.setClass1(klass);
        return school;
    }
}
