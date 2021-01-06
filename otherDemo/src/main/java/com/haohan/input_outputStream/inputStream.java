package com.haohan.input_outputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author haohan
 * @date 2020/10/22 14:37
 */
public class inputStream {

    public static void main(String[] args) throws IOException {
        OutputStream out = null;
        out = new FileOutputStream("C:\\Users\\hao'han\\Desktop\\测试.txt");
        String str = "hello world!";
        byte[] bytes = str.getBytes();
        out.write(bytes);

        out.close(); 


//        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
//        String hostAddress = inetAddress.getHostAddress();
//        String hostName = inetAddress.getHostName();
//        System.out.println("hostAddress : " + hostAddress + "---" + "hostName : " + hostName);
    }

}
