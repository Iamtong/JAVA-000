package com.tong.mall.mapper;

import com.tong.mall.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author Admin
 */
@Mapper
public interface UserMapper {
    @Select("select id,user_name,password,status from users where id=#{id}")
    public User getUserById(@Param("id") Integer id);

    @Select("select id,user_name,password,status from users where username=#{username}")
    public User getUserByName(@Param("username") String username);

    @Select("select * from users where is_lock=#{status} order by id desc limit #{start},#{limit}")
    public List<User> findAll(@Param("status") Integer status, @Param("start") Integer start, @Param("limit") Integer limit);

    @Insert("insert into users(user_name,user_pass,phone,real_name,created_time,updated_time,is_lock) values(#{user_name},#{user_pass},#{phone},#{real_name},now(),now(),0)")
    @SelectKey(keyProperty = "id",before = false,resultType = int.class,statement = "select last_insert_id()")
    public int addUser(User User);

    /**
     * 更新余额
     * @param id
     * @param money
     * @return
     */
    @Update("update users set balance = balance + #{money} where id = #{id}")
    public int updateBalance(@Param("id") Integer id,@Param("money") Integer money);
}
