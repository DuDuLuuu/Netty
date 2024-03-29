package com.jdb.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * @Author:JDb
 * @Date:2019/11/10
 * 服务器demo
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //接受请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理请求
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).childHandler(new TestServerInitalizer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
