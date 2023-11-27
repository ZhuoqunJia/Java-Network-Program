package Socket聊天室.客户端与客户端之间通信;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: JZQ
 * @Date: 2023/11/27 15:38
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        System.out.println("客户端已经成功启动");
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 8080);
            OutputStream outputStream = socket.getOutputStream();
            Scanner input = new Scanner(System.in);
            while (true){
                System.out.print("请输入内容：");
                /**
                 * input.next(): 获取控制台输入内容，以空格分隔
                 * input.nextLine(): 获取控制台整行输入内容
                 *
                 * 只要是next方法都会造成线程阻塞
                 */
                String content = input.nextLine();
                content = "客户端说：" + content;
                outputStream.write(content.getBytes());


                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                System.out.println(new String(bytes, 0, inputStream.read(bytes)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
