package com.tong.demo2.mapper;

import com.tong.demo2.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Admin
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select id,user_name,user_pass,phone,real_name,is_lock,created_time,updated_time from users where id=#{id}")
    User getUserById(@Param("id") Integer id);

    @Select("select id,user_name,user_pass,phone,real_name,is_lock,created_time,updated_time from users where user_name=#{userName}")
    User getUserByName(@Param("userName") String username);

    @Select("select * from users where is_lock=#{isLock} order by id desc limit #{start},#{limit}")
    List<User> findAll(@Param("isLock") Integer status, @Param("start") Integer start, @Param("limit") Integer limit);

    @Insert("insert into users(user_name,user_pass,phone,real_name,created_time,updated_time,is_lock) values(#{userName},#{userPass},#{phone},#{realName},now(),now(),0)")
    @SelectKey(keyProperty = "id",before = false,resultType = int.class,statement = "select last_insert_id()")
    int addUser(User User);

    /**
     * 更新余额
     * @param id
     * @param money
     * @return
     */
    @Update("update users set balance = balance + #{money} where id = #{id}")
    int updateBalance(@Param("id") Integer id,@Param("money") Integer money);
}
