package com.tong.week5.work6_db.jdbc;

import java.sql.*;

public class JdbcTxDemo {
    public static void main(String[] arg) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8","root","root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.setAutoCommit(false);
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
            preparedStatement.setString(1,"test2test2test2test2test2test2test2test2test2test2");
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
            connection.commit();
            System.out.println("事务提交成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
            System.out.println("事务回滚");
        }




        connection.close();
    }
}
