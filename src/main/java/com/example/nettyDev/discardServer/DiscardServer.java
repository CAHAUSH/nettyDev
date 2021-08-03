package com.example.nettyDev.discardServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃掉所有接收到的数据
 * */
public class DiscardServer {
    private int port;
    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() {
        /** 主从reactor模式 */
        //NioEventLoopGroup 多线程事件循环，用来处理IO操作
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//注册boss接收的连接，并且处理接收到的流量
        try{
            //ServerBootstrap是设置server的帮助类；也可以通过channel直接来创建，但是比较枯燥和繁琐
            ServerBootstrap b = new ServerBootstrap();
            //builder 模式构件对象
            b.group(bossGroup,workerGroup)
                    //指定ServerChanle为NioServerSocketChannel，用于实例化一个新的Channel来接收发送过来的连接
                    .channel(NioServerSocketChannel.class)
                    //所有新接收的Channel都会经过此处指定的handler；
                    //ChannelInitializer是一个特殊的handler，用于帮助用户配置一个新的Channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //可以通过添加handler来配置Channel的pipeline，来完成程序处理
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    //可以指定channel的实现参数，用于NioServerSocketChannel通道
                    .option(ChannelOption.SO_BACKLOG,128)
                    //用于NioServerSocketChannel接收到的通道
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            //绑定端口，开始接收来到的数据
            ChannelFuture f = b.bind(port).sync();
            //
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}
