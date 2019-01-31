package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ContactProjectServer {

    public static void main(String[] arg) throws IOException, FileNotFoundException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is waiting on " + serverSocket.getInetAddress());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has connected");
                //создание отдельного потока для обмена данными с соединившимся клиентом
                ServerThread thread = new ServerThread(clientSocket);
                // запуск потока
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

