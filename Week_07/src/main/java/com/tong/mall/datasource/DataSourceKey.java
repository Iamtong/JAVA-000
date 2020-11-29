package com.tong.mall.datasource;

import java.util.function.Supplier;

/**
 * @author Admin
 */

public enum DataSourceKey implements Supplier {
    master,
    slave;

    @Override
    public Object get() {
        return null;
    }
}
