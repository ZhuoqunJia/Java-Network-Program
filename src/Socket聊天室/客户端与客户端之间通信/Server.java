package Socket聊天室.客户端与客户端之间通信;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: JZQ
 * @Date: 2023/11/27 15:38
 * @Description:
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("当前线程为：" + Thread.currentThread().getName());
        System.out.println("服务端开始启动");
        try {
            ServerSocket server = new ServerSocket(8080);
            Socket accept = server.accept();
            System.out.println("服务端已经启动");
            Scanner input = new Scanner(System.in);
            InputStream inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];
            OutputStream outputStream = accept.getOutputStream();
            while (true){

                System.out.println(new String(bytes, 0, inputStream.read(bytes)));

                System.out.print("请输入：");
                String content = input.nextLine();
                content = "服务端说：" + content;
                outputStream.write(content.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
