package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: JZQ
 * @Date: 2023/11/27 14:31
 * @Description: 1、客户端给服务端发送一个文字信息
 * 2、服务端给客户端返回一个成功的信息
 */
public class Server {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        try {
            System.out.println("服务端已经启动");
            ServerSocket server = new ServerSocket(8080);
            //等待客户端连接,accept()方法会阻止程序继续往下执行
            for (int i = 0; i < 5; i++) {
                Socket accept = server.accept(); //只是阻塞了当前线程
                InputStream inputStream = accept.getInputStream();
                byte[] bytes = new byte[1024];
                int readCount;
//                while ((readCount = inputStream.read(bytes)) != -1){
//                    System.out.println("服务端收到了客户端的信息：" + new String(bytes,0,readCount));
//                }
                readCount = inputStream.read(bytes);
                System.out.println("服务端收到了客户端的信息：" + new String(bytes, 0, readCount));

                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("服务端已经收到了信息".getBytes());
                System.out.println("服务端已经启动完毕");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
