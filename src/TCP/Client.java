package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author: JZQ
 * @Date: 2023/11/27 14:40
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        try {
            System.out.println("客户端已经成功启动");
            Socket socket = new Socket(InetAddress.getLocalHost(), 8080);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("客户端说：你好，服务端".getBytes());

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int readCount;
            readCount = inputStream.read(bytes);
            System.out.println("客户端收到了服务端的信息：" + new String(bytes, 0, readCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
