package homework_01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    private Socket socket;
    private String [] citats = {
            "Обязательно дружите с теми, кто лучше вас. Будете мучиться, но расти.",
            "Никогда не отказывайся от того, что заставляет тебя улыбаться.",
            "Столько есть всего, о чём надо подумать. Зачем забивать себе голову тем, чего уже не вернёшь, — надо думать о том, что ещё можно изменить.",
            "Нужно иметь что-то общее, чтобы понимать друг друга, и чем-то отличаться, чтобы любить друг друга.",
            "Жизнь состоит не в том, чтобы найти себя. Жизнь состоит в том, чтобы создать себя.",
            "Будьте заняты. Это самое дешевое лекарство на земле — и одно из самых эффективных.",
            "Ты — это то, что ты делаешь. Ты — это твой выбор. Тот, в кого себя превратишь.",
            "Несчастным или счастливым человека делают только его мысли, а не внешние обстоятельства. Управляя своими мыслями, он управляет своим счастьем.",
            "Любовь не терпит объяснений. Ей нужны поступки.",
            "Жить надо так, чтобы тебя помнили и сволочи.",
            "Не ищите злой умысел там, где все можно объяснить глупостью.",
            "Логика приведет вас из пункта А в пункт Б. Воображение приведет вас куда угодно.",
            "Работа не волк. Никто не волк. Только волк — волк.",
            "Настоящий мужчина, как ковер тети Зины — с каждым годом лысеет.",
            "Если закрыть глаза, становится темно.",
            "«Жи-ши» пиши от души.",
            "Кто рано встает — тому весь день спать хочется."
    };

    public void setSocket(Socket socket){
        this.socket = socket;
        citation();
    }


    public static void main(String[] args) {

        ServerSocket serverSocket = null;


        try{
            try{
                serverSocket = new ServerSocket(5555, 0, InetAddress.getByName("localhost"));
                System.out.println("Сервер запущен");

                while(true){
                    Socket socket = serverSocket.accept();
                    System.err.println("\n\nКлиент " + socket + " принят");

                    new Server().setSocket(socket);
                }

            } catch (Exception e) {
                System.out.println("Исключение: " + e);
            }
        } finally {
            try{
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void citation(){
        try{
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String question;
            String answer;

            while(true){
                question = "Хотите получить цитату дня? (Yes/No)";
                System.out.print("Отправляю пользователю вопрос с текстом: \"" + question + "\"");

                dos.writeUTF(question);
                dos.flush();

                System.out.println("Отправил");

                answer = dis.readUTF();   // считывает строку в UTF-8
                System.out.println("Клиент ответил: \"" + answer + "\"");
                if(answer.equalsIgnoreCase("yes")){
                    dos.writeUTF(citats[new Random().nextInt(0, citats.length)]);
                    dos.flush();
                }


                System.out.println();
                if(answer.equalsIgnoreCase("no")){
                    dos.writeUTF("Спасибо за использование ресурса!");
                    dos.flush();
                    socket.close();
                    System.out.print("Клиент закрыл соединение");
                    break;
                }
            }
        } catch(Exception e){
            System.out.println("Исключение: " + e);
        }
    }
}
