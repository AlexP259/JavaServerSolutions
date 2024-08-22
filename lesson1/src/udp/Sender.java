package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) {
        DatagramSocket datagramSocket;
        Scanner input = new Scanner(System.in);

        try{
            datagramSocket = new DatagramSocket();
            while(true){
                /* Отправим сообщение серверу */
                System.out.print("Введите сообщение серверу: ");
                String s = input.nextLine();    // Пользователь вводит сообщение
                byte[] b = s.getBytes();  // Преобразуем строку в массив байт, т.к. пакет можно сделать только из него

                /* создаем пакет и снабжаем информацией куда он отправится */
                DatagramPacket dPacket = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"),8965);
                datagramSocket.send(dPacket);   // отправляем пакет


                /* Получим данные от сервера */
                byte[] buf = new byte[1024];
                DatagramPacket d = new DatagramPacket(buf, b.length);
                datagramSocket.receive(d);
                byte[] data = d.getData();
                s = new String(data, 0, d.getLength());
//                s = Arrays.toString(data);
                System.out.println("Сервер: " + d.getAddress().getHostAddress() + " порт " + d.getPort() +
                        " получено: " + s);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
