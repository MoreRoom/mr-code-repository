package com.zspace.netty.timer.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: MoreRoom
 * @date: 2018/11/22 14:22
 */
public class TimerServer {

    private static final int port = 30122;

    private static final String REQUEST_STRING = "query current server time.";

    public static void serverStart() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("time server started success...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ResponseTask(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class ResponseTask implements Runnable {

        private Socket socket;

        public ResponseTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            PrintWriter printWriter = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                byteArrayOutputStream = new ByteArrayOutputStream();
                String requestString;
                StringBuffer stringBuffer = new StringBuffer();
                while (null != (requestString = bufferedReader.readLine())) {
                    stringBuffer.append(requestString);
                }
                printWriter = new PrintWriter(socket.getOutputStream());
                if (REQUEST_STRING.equalsIgnoreCase(requestString)) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    printWriter.println(simpleDateFormat.format(new Date()).getBytes());
                } else {
                    printWriter.println("bad request...".getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
