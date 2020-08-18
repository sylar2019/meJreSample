package me.sample.io.coap.client;

/**
 * @author : sylar
 * @fullName : me.sample.io.coap.client.Foo
 * @createDate : 2020/8/14
 * @description :
 * @copyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public class Foo {
    String data;

    public Foo(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
