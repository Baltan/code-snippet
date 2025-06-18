package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9527);
        Socket socket = server.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String fromClient;
        while (true) {
            fromClient = br.readLine();
            if (fromClient != null && !"".equals(fromClient)) {
                System.out.println("来自客户端的消息：" + fromClient);
                System.out.println("*************************");
            }
            if ("end".equals(fromClient)) {
                break;
            }
        }
        socket.close();
    }
}
