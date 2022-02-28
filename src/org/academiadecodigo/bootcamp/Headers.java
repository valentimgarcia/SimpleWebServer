package org.academiadecodigo.bootcamp;

public class Headers {

    public static String getHeader200(int size, String extention) {
        if (extention.equals("html")) {
            return "HTTP/1.1 200 Document Follows\r\nContent-Type: text/html; charset=UTF-8\r\nContent-Length:" + size + "\r\n\r\n";
        }
        return "HTTP/1.1 200 Document Follows\r\nContent-Type: " + extention + "; charset=UTF-8\r\nContent-Length:" + size + "\r\n\r\n";
    }

    public static String getHeader400(int size) {
        return "HTTP/1.1 404 Not Found\r\nContent-Type: text/html; charset=UTF-8\r\nContent-Length:" + size + "\r\n\r\n";
    }
}
