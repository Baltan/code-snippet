package socket_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(InetAddress.getByName("192.168.199.219"), 9527); // 127.0.0.1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
        String toServer;
        while (true) {
            System.out.println("请输入要发送的消息：");
            toServer = br.readLine();
            pw.println(toServer);
            pw.flush();
            System.out.println("###########################");
            if ("end".equals(toServer)) {
                break;
            }
        }
        clientSocket.close();
    }
}
