package homework_01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int serverPort = 5555;
    private static final String localhost = "127.0.0.1";


    public static void main(String[] args){
        Socket socket = null;
        try{
            try{
                System.out.println("Добро пожаловать на клиентскую сторону\n>> Подключение к " +
                        "серверу");
                socket = new Socket(InetAddress.getByName(localhost), serverPort);
                System.out.println(">> Соединение установлено");

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader keyboard = new BufferedReader(isr);

                String question;
                String answer;
                while (true) {
                    question = in.readUTF();        // мы считываем вопрос, хотим ли мы цитату
                    System.out.print(question + " ");

//                    out.writeUTF(new Scanner(System.in).nextLine());
//                    out.flush();
                    answer = keyboard.readLine();
                    out.writeUTF(answer);
                    out.flush();

                    if (answer.endsWith("no")) {
                        System.out.println("\n" + answer);
                        keyboard.close();
                        isr.close();
                        in.close();
                        out.close();
                        break;
                    } else {
                        answer = in.readUTF();
                        System.out.println("\nСервер отправил мне эту строку:\n\t" + answer);
                        System.out.println();
                    }
                }
            } catch (Exception e) {
                System.out.println("Исключение: " + e);
            }
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
