package com.imooc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类,专门接收服务器端响应信息
 */
public class NioClientHandler implements Runnable{

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            for (;;){

                int readyChannel = selector.select();

                if (readyChannel == 0) continue;

                /**
                 * 获取可用的channel集合
                 */
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator iterator = selectionKeys.iterator();

                while (iterator.hasNext()){

                    /**
                     * SelectionKey实例
                     */
                    SelectionKey selectionKey = (SelectionKey) iterator.next();

                    /**
                     * 移除Set中当前的SelectionKey
                     */
                    iterator.remove();

                    /**
                     * 如果是可读事件
                     */
                    if (selectionKey.isReadable()){
                        readHandler(selectionKey,selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可读事件处理器
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {

        /**
         * 从SelectionKey中获取到已就绪的Channel
         */
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        /**
         * 创建buffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        /**
         * 循环读取客户端请求信息
         */
        String response = "";
        while (socketChannel.read(byteBuffer)>0){
            /**
             * 切换buffer为读模式
             */
            byteBuffer.flip();

            /**
             * 读取buffer中的内容
             */
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }

        /**
         * 将channel再次注册到selector上, 监听他的可读事件
         */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /**
         * 将客户端发送的请求信息 广播给其他客户端
         */
        if (response.length()>0){
            // 将客户端响应信息打印到本地
            System.out.println(response);
        }
    }
}
