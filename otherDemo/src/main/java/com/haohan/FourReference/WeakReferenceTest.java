package com.haohan.FourReference;

import java.lang.ref.WeakReference;

/**
 * @author com.haohan
 * 04/13/2020 - 09:57 上午
 * 弱引用 --- 每次GC时都会回收
 * ThradLocal就是使用的弱引用
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[100 * 1024 * 1024]);

        System.out.println(weakReference.get());

        //第一次GC
        System.gc();

        System.out.println(weakReference.get());

        //第二次GC
        System.gc();

        System.out.println(weakReference.get());

        ThreadLocal tl = new ThreadLocal();
        Object o = tl.get();
    }

}
