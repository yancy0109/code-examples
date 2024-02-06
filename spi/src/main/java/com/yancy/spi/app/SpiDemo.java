package com.yancy.spi.app;

import com.yancy.spi.DataStorage;

import java.util.ServiceLoader;

/**
 * @author yancy0109
 * @date: 2024/2/6
 */
public class SpiDemo {

    public static void main(String[] args) {
        ServiceLoader<DataStorage> serviceLoader = ServiceLoader.load(DataStorage.class);
        serviceLoader.forEach(dataStorage -> System.out.println(dataStorage.search("SPI_Test, Yes or NO?")));
    }
}
