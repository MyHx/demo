package com.hx.base.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;

public class NettyClient {

    public static void main(String[] args) throws Exception {
        start();
    }

    /**
     * 创建客户端实例并向服务端发送连接请求
     */
    public static void start() {

        // 创建EventLoopGroup，用于处理客户端的I/O操作
        EventLoopGroup groupThread = new NioEventLoopGroup();

        try {
            // 创建Bootstrap实例，客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(groupThread);
            // 设置服务端Channel类型为NioSocketChannel作为通道实现
            bootstrap.channel(NioSocketChannel.class);
            // 设置客户端处理
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });
            // 向服务端发送连接请求
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8888).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 优雅地关闭线程
            groupThread.shutdownGracefully();
        }
    }
}
