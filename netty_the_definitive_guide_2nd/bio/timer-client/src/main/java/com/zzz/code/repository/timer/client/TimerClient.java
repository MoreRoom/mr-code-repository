package com.zzz.code.repository.timer.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @auther: MoreRoom
 * @date: 2018/11/22 14:22
 */
public class TimerClient {

    private static final int port = 30122;

    private static final String REQUEST_STRING = "query current server time.\r\n";

    public static void clientRequest() throws IOException {
        Socket socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", port);
        socket.connect(inetSocketAddress);
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(REQUEST_STRING);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseString = bufferedReader.readLine();
            System.out.println(responseString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        clientRequest();
    }

}
