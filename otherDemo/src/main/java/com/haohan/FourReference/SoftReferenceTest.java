package com.haohan.FourReference;

import java.lang.ref.SoftReference;

/**
 * @author com.haohan
 * 04/13/2020 - 09:45 上午
 * 软引用 --- 只有在内存不足时才会被GC回收
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        //模拟缓存
        byte[] bytes = new byte[100 * 1024 * 1024];
        SoftReference<byte[]> cacheRef = new SoftReference<byte[]>(bytes);

        System.out.println(cacheRef.get());
        //第一次GC
        System.gc();
        cacheRef = null;

        System.out.println(cacheRef.get());

        //第二次GC
        System.gc();

        System.out.println(cacheRef.get());

    }

}
