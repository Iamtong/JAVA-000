package com.tong.mall.service;

import com.tong.mall.entity.User;
import com.tong.mall.exception.ServiceException;
import com.tong.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Admin
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = DataAccessException.class)
    public int add(User user) throws ServiceException {
        int i = userMapper.addUser(user);
        if (i <= 0){
            throw new ServiceException("写入失败");
        }
        return i;
    }

    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    public List<User> getUserList(int start,int limit){
        return userMapper.findAll(0,start,limit);
    }

}
