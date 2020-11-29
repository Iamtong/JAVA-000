package com.tong.mall.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author Admin
 */
public class ServiceException extends DataAccessException {
    public ServiceException(String msg) {
        super(msg);
    }
}
