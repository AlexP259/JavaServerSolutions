import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        try(ServerSocket srvSocket = new ServerSocket(8123,0, InetAddress.getByName("localhost"));
            Socket socket = srvSocket.accept();
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream())){
            writer.println("hello!");
//            writer.write("hello!");   // сработает только с вызванным вручную методом flush()
//            writer.flush();
//            writer.close();
            
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
