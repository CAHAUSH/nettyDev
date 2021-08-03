package com.example.nettyDev.ServerTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 处理 server-side channel
 * window上使用telnet localhost 8080 即可进行数据发送、服务接收测试
 * */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    //不接受客户端消息，在连接建立的时候，发送一条消息
    //在连接被建立并且就绪之后，channelActive方法就会被调用
    //在此我们发送一个32bit整数来代表当前的时间
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //为了发送消息，我们需要分配一个新的缓冲区用来存放消息
        //我们需要一个容量最少为4字节的ByteBuf
        //通过ChannelHandlerContext.alloc()方法，我们可以获取一个ByteBufAllocator 字节缓冲分配器
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/ 100L + 2208988800L));
        //写入已经创建的消息
        /**
         * As usual, we write the constructed message.
         *
         * But wait, where's the flip? Didn't we used to call java.nio.ByteBuffer.flip() before sending a message in NIO?
         * ByteBuf does not have such a method because it has two pointers;
         * one for read operations and the other for write operations.
         * The writer index increases when you write something to a ByteBuf while the reader index does not change.
         * The reader index and the writer index represents where the message starts and ends respectively.
         *
         * In contrast, NIO buffer does not provide a clean way to figure out where the message content starts and ends without calling the flip method.
         * You will be in trouble when you forget to flip the buffer because nothing or incorrect data will be sent.
         * Such an error does not happen in Netty because we have different pointer for different operation types.
         * You will find it makes your life much easier as you get used to it -- a life without flipping out!
         *
         *
         * ChannelHandlerContext.write()(and writeAndFlush()) 方法返回一个ChannelFuture对象
         * ChannelFuture代表一个还没有发生的IO操作。 在Netty中消息是异步的，也就意味着任何请求操作可能都还没有被执行。
         * 例如，以下代码可能出现，连接应关闭，但是消息还没有被发送的情况
         * Channel ch = ...;
         * ch.writeAndFlush(message);
         * ch.close();
         *
         *因为我们需要在ChannelFuture（由write或者writeAndFlush方法返回）结束之后，再调用close方法。
         * ChannelFuture操作完成后，会通知它的监听器 。
         *
         * 另外，需要注意close方法也可能不会立即关闭连接，并且返回一个ChannelFuture
         * */
        final ChannelFuture f = ctx.writeAndFlush(time);

        //通过向返回的ChannelFuture添加一个ChannelFutureListener，来获取请求处理结束的通知事件
        //
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                assert f == channelFuture;
                ctx.close();
            }
        });
        //我们也可以使用预定义的listener来代替上面操作，来简化代码
        //f.addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
