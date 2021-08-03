package com.example.nettyDev.ServerEcho;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 处理 server-side channel, 将接收到的数据 返回给client
 * window上使用telnet localhost 8080 即可进行数据发送、服务接收测试
 * */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    //使用ChannelHandlerContext可以触发多种IO事件及操作
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        //((ByteBuf) msg).release(); // (3)

        /**正常的处理流程：1、先读取消息； 2、finally 消费调消息*
        ByteBuf in = (ByteBuf) msg;
        try{
            //do somteing
            while (in.isReadable()){
                System.out.println((char) in.readByte());
                System.out.flush();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
         */
        System.out.println(msg);
        ctx.write(msg);
        ctx.flush();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
