package demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: JZQ
 * @Date: 2023/11/27 14:07
 * @Description:
 */
public class InetAddressTest01 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        try {
            //知道对方的IP情况下使用
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            System.out.println(inetAddress);
            System.out.println(inetAddress.getHostName());

            //获取本机的机器名和IP地址
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
            System.out.println(localHost.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
