package com.jdb.netty7;

import com.google.protobuf.Descriptors;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

/**
 * @Author:JDb
 * @Date:2019/11/16
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        System.out.println("传输数据类型："+dataType);
        Map<Descriptors.FieldDescriptor, Object> allFields = null;
        switch (dataType) {
            case PersonType:
                allFields=msg.getPerson().getAllFields();
                break;
            case DogType:
                allFields=msg.getDog().getAllFields();
                break;
        }
        allFields.forEach((k,v)-> System.out.println(k+":"+v));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功："+ctx.channel().remoteAddress());
    }
}
