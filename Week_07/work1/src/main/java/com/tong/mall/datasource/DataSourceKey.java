package com.tong.mall.datasource;

import java.util.function.Supplier;

/**
 * @author Admin
 */

public enum DataSourceKey implements Supplier {
    master,
    slave1,
    slave2;

    @Override
    public Object get() {
        return null;
    }
}
