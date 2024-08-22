import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Получаем HTML из гугла с помощью специальных классов */

        try{
//            URLConnection connection = new URL("google.com").openConnection(); // устаревший
            URLConnection connection = new URI("http://www.google.com").toURL().openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }

            System.out.println();

            // получим хедеры
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }


        } catch(Exception e){
            System.out.println(e.getMessage());
        }


//        try(Socket socket = new Socket()){
//            socket.connect(new InetSocketAddress("localhost", 8123));
//            Scanner scanner = new Scanner(socket.getInputStream());
//            while(scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//            }
//        } catch(IOException e){
//            System.out.println(e.getMessage());
//        }
    }
}