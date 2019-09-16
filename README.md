NIO网络编程模型 {编程模型==>对模型共性的抽象==>把动物装冰箱}
BIO 编程模型. 缺点:{阻塞式IO , 弹性伸缩能力差 , 多线程消耗资源 }
NIO 编程模型. 改进:{非阻塞式IO , 弹性伸缩能力强 , 单线程节省资源}
通道(channel) {简介: 双向性 , 非阻塞性 , 操作唯一性}, {实现: 文件类(FileChannel) , UDP类(DatagramChannel) , TCP类(ServerSocketChannerl, SocketChannel)}
缓冲区(Buffer) {简介: 读写Channel中数据 , 块内存区域}, {属性: 容量(Capacity) , 上限(Limit) , 位置(Position), 标记(Mark)}
多路复用器(Selector) {简介: I/O事件就绪选择}, {NIO网络编程基础之一}
选择键(SelectionKey) {简介: 四种就绪状态常量, 有价值的属性}
NIO网络编程实战 {1.创建Selector. 2.创建ServerSocketChannel并绑定监听端口. 3.将Channel设置为非阻塞模式. 4.将Channel注册到Selector上,监听连接事件. 5.循环调用Selector的select方法,检测就绪情况. 6.调用SelectKeys方法获取就绪Channel集合. 7.判断就绪事件种类,调用不同业务处理方法}
NIO网络编程缺陷 { NIO类库和API繁杂; 可靠性能力补齐,工作量和难度非常大; Selector空轮询,CPU 100%}
