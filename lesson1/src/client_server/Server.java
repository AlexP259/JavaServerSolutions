package client_server;

import org.w3c.dom.ls.LSOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private static final int port = 5555;
    private final String MSG = "Клиент '%d' отправил мне сообщение: \n\t";
    private final String CONN = "Клиент '%d' закрыл соединение";

    private Socket socket;
    private int num;

    public void setSocket(int num, Socket socket){
        this.num = num;
        this.socket = socket;

        start();
    }

    public void run(){
        try{
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String line;
            while(true){
                line = dis.readUTF();   // считывает строку в UTF-8
                System.out.printf(MSG, num);
                System.out.println(line);
                System.out.println("Отправляю обратно...");
                dos.writeUTF("Сервер получает текст: " + line);
                dos.flush();
                System.out.println();
                if(line.equalsIgnoreCase("quit")){
                    socket.close();
                    System.out.printf(CONN, num);
                    break;
                }
            }
        } catch(Exception e){
            System.out.println("Исключение: " + e);
        }
    }

    public static void main(String[] args) {
        ServerSocket srvSocket = null;
        try{
            try{
                int i = 0;
                InetAddress ia = InetAddress.getByName("127.0.0.1");
                srvSocket = new ServerSocket(port, 0, ia);
                System.out.println("Сервер запущен");

                while(true){
                    Socket socket = srvSocket.accept(); // accept ждет пока подключится клиент и возвращает информацию
                    // о нем типа Socket[addr=/127.0.0.1,port=54470,localport=5555]. Порт клиента определяется ОС
                    // компьютера клиента
                    System.err.println("\n\nКлиент " + socket + " принят");
                    new Server().setSocket(i++, socket);    // именно здесь происходит подтверждение о доставке данных
                }
            } catch (Exception e) {
                System.out.println("Исключение: " + e);
            }
        } finally {
            try{
                if(srvSocket != null){
                    srvSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
