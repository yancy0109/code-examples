package com.yancy.spi.other;

import com.yancy.spi.DataStorage;

/**
 * @author yancy0109
 * @date: 2024/2/7
 */
public class OtherStorage implements DataStorage {

    @Override
    public String search(String key) {
        return "[Other] [Search] " + key + ", Result: No";
    }
}
