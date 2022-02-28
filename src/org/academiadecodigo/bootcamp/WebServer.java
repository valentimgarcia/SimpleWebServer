package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class WebServer {
    private final int PORT = 4042;

    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.listen();
    }

    public void listen() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket client = serverSocket.accept();

                BufferedReader inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream outputStream = client.getOutputStream();

                String separate = inputReader.readLine().split(" ")[1];
                System.out.println(separate);
                separate = separate.equals("/") ? "/index.html" : separate;

                File myBeautifulFile = new File("www" + separate);

                URLConnection url = myBeautifulFile.toURL().openConnection();

                String header;
                if (!myBeautifulFile.exists()) {
                    myBeautifulFile = new File("www/404.html");
                    url = myBeautifulFile.toURL().openConnection();
                    header = Headers.getHeader400(url.getContentLength());
                } else {
                    header = Headers.getHeader200(url.getContentLength(), separate.split("\\.")[1]);
                }

                FileInputStream inputStream = new FileInputStream(myBeautifulFile);
                outputStream.write(header.getBytes(StandardCharsets.UTF_8));

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    System.out.println("Bytes read: " + bytesRead);
                }
                outputStream.flush();
            }

        } catch (IOException e) {
            System.err.println("Server connection error: " + e.getMessage());
        }
    }
}