package com.haohan.FourReference;

/**
 * @author com.haohan
 * 04/13/2020 - 09:38 上午
 * 测试对象
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("M对象被回收......");
        super.finalize();
    }

}
