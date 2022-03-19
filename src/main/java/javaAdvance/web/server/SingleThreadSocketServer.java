package javaAdvance.web.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class SingleThreadSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8801);
        while(true) {
            try {
                System.out.println("Socket is running in port:" + 8801);
                Socket socketRequest= server.accept();
                service(socketRequest);

            }catch (IOException e)
            {

                e.printStackTrace();
            }

        }
    }

    private static void service(Socket socketRequest) {
        try {
            PrintWriter printWriter = new PrintWriter(socketRequest.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String bodyContent = "hi, King of return!";
            printWriter.println("Content-Length:" + bodyContent.length());
            printWriter.println();
            printWriter.write(bodyContent);
            printWriter.close();
            socketRequest.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
