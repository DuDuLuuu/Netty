package com.jdb.netty6;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Author:JDb
 * @Date:2019/11/16
 * 用protobuf将数据格式化为可传输的形式
 * 编译指令 protoc --java_out=src/main/java src/main/java/com/jdb/netty6/Student.proto
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("JDb").setAge(18).setAddress("上海").build();
        //把对象转成byte数组
        byte[] bytes = student.toByteArray();
        //把byte数组解析成对象
        DataInfo.Student student2 = DataInfo.Student.parseFrom(bytes);
        //打印测试
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
