package com.zspace.netty.timer.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            InputStream inputStream = null;
            OutputStream outputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                inputStream = socket.getInputStream();
                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                String requestString = new String(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.flush();
                outputStream = socket.getOutputStream();
                if (REQUEST_STRING.equalsIgnoreCase(requestString)) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    outputStream.write(simpleDateFormat.format(new Date()).getBytes());
                } else {
                    outputStream.write("bad request...".getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
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
