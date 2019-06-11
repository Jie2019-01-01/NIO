package com.imooc;

import java.io.IOException;

/**
 * @version 1.0
 * @Author: L.W.J
 * @Date: 2019/6/11
 * @Description com.imooc
 */
public class BClient {
    public static void main(String[] args) throws IOException {
        new NioClient().start("BClient");
    }
}
