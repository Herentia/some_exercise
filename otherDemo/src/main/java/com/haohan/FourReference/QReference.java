package com.haohan.FourReference;

/**
 * @author com.haohan
 * 04/13/2020 - 09:38 上午
 * 强引用
 */
public class QReference {

    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.out.println(m);

        System.gc();

        System.out.println(m);
    }

}
