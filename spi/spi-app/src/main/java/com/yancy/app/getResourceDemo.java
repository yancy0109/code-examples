package com.yancy.app;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author yancy0109
 * @date: 2024/2/7
 */
public class getResourceDemo {
    public static void main(String[] args) throws IOException {
        Enumeration<URL> systemResources = ClassLoader.getSystemResources("META-INF/services/com.yancy.spi.DataStorage");
        while (systemResources.hasMoreElements()) {
            URL url = systemResources.nextElement();
            System.out.println(url.getFile());
        }
    }
}
