package Socket聊天室.客户端与客户端之间通信;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
            //保存连接的客户端Socket对象
            ArrayList<Socket> clients = new ArrayList<>();
            while (true){
                Socket accept = server.accept();
                //添加Socket对象
                clients.add(accept);

                System.out.println("服务端已连接一个客户端");
                Scanner input = new Scanner(System.in);
                InputStream inputStream = accept.getInputStream();
                byte[] bytes = new byte[1024];

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            try{
                                System.out.println("当前线程为：" + Thread.currentThread().getName());
                                String r = new String(bytes, 0, inputStream.read(bytes));
                                if(r.endsWith("bye")){
                                    break;
                                }
                                System.out.println(r);

                                OutputStream outputStream = null;
                                //发送给多有客户端
                                for (Socket item:
                                     clients) {
                                    outputStream = item.getOutputStream();
                                    outputStream.write(r.getBytes());
                                }

//                                System.out.print("请输入内容：");
//                                String content = input.nextLine(); //阻塞
//                                content = "服务端说：" + content;
//                                outputStream.write(content.getBytes());
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });

                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
