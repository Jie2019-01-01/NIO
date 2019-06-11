package com.imooc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * nio客户端
 */
public class NioClient {

    /**
     * 启动
     */
    public void start(String nickName) throws IOException {
        /**
         * 连接服务器端
         */
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8080));

        /**
         * 接收服务器端响应
         */
        // 新开线程,专门接收服务器端的响应数据
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        /**
         * 向服务器端发送数据
         */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String request = scanner.nextLine();
            if (request!=null && request.length()>0){
                socketChannel.write(Charset.forName("UTF-8").encode(nickName+":"+request));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
    }
}
