package com.jdb.netty7;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Author:JDb
 * @Date:2019/11/16
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通道连接成功后，客户端向服务器发送jdb对象
        //旧方式：指定传输单一类型
        MyDataInfo.Person jdb = MyDataInfo.Person.newBuilder().setName("JDb").setAge(18).setAddress("上海").build();
        MyDataInfo.Dog xg = MyDataInfo.Dog.newBuilder().setDogName("雪糕").setDogAge(1).build();
//        ctx.channel().writeAndFlush(xg);

        //新方式：枚举定义传输通用类型
        MyDataInfo.MyMessage personData = MyDataInfo.MyMessage.newBuilder()
                .setDataType(MyDataInfo.MyMessage.DataType.PersonType).setPerson(jdb).build();
        MyDataInfo.MyMessage dogData = MyDataInfo.MyMessage.newBuilder()
                .setDataType(MyDataInfo.MyMessage.DataType.DogType).setDog(xg).build();

        int i = new Random().nextInt(2);
        switch (i) {
            case 0:
                System.out.println("发送jdb");
                ctx.channel().writeAndFlush(personData);
                break;
            case 1:
                System.out.println("发送xg");
                ctx.channel().writeAndFlush(dogData);
                break;
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功");
    }

}
