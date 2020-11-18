package com.tong.week5.work6_db.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.*;

public class HikariDemo {
    public static void main(String[] args) throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(8);
        hikariConfig.setPoolName("pool");
        HikariPool hikariPool = new HikariPool(hikariConfig);

        Connection connection = hikariPool.getConnection();

        //查询
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()){
            System.out.println("Id:" + resultSet.getInt("id") + ",Name:" + resultSet.getString("name") + ",Age:" + resultSet.getString("name"));
        }
        //增加
        // 新增一条记录
        String insert = "insert into users (name, age) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,"test2");
        preparedStatement.setInt(2,20);
        preparedStatement.execute();
        System.out.println(insert);
        // 修改一条数据
        String update = "update users set age=? where id=?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(update);
        preparedStatement2.setInt(1,60);
        preparedStatement2.setInt(2,1);
        preparedStatement2.execute();
        System.out.println(update);
        // 删除数据
        String delete = "delete from users where id=?";
        PreparedStatement preparedStatement3 = connection.prepareStatement(delete);
        preparedStatement3.setInt(1,2);
        preparedStatement3.execute();
        System.out.println(delete);

        connection.close();

    }
}
