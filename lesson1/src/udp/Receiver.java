package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
    public static void main(String[] args) {
        try{
            DatagramSocket dSocket = new DatagramSocket(8965);  // создали датаграмСокет и передали ему порт
            byte[] bytes = new byte[1024]; // массив на 1024 байта для получения входящих данных, 1024 - размер пакета
            DatagramPacket dPacket = new DatagramPacket(bytes, bytes.length);   // сделали пакет на 1024 байта
            System.out.println("Ожидаем данные...");

            while(true){
                /* Получим данные от клиента */
                dSocket.receive(dPacket);   // udp-сокет получает данные и кладет их в dPacket
                byte[] data = dPacket.getData();    // создаем еще один массив и перекладываем в него байты,
                // извлеченные из пакета
                String s = new String(data, 0, dPacket.getLength());    // из массива байт делаем строку
                System.out.println("Сервер получил: " + s);


                /* Отправим данные обратно клиенту (Sender) */
                DatagramPacket dp = new DatagramPacket(data, data.length, dPacket.getAddress(), dPacket.getPort());
                dSocket.send(dp);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
