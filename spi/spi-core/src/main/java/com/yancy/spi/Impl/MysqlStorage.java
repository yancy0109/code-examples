package com.yancy.spi.Impl;

import com.yancy.spi.DataStorage;

/**
 * @author yancy0109
 * @date: 2024/2/6
 */
public class MysqlStorage implements DataStorage {
    @Override
    public String search(String key) {
        return "[Mysql] [Search] " + key + ", Result: No";
    }

}
