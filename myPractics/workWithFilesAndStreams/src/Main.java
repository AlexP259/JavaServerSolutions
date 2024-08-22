import java.io.*;

public class Main {
    public static void main(String[] args) {

        /* READER */

        FileReader reader = null;
        BufferedReader bufferedReader = null;

        char readerResult = 0;
        StringBuilder bufferResult = new StringBuilder();
        char[] readerResultArray = new char[50];
        String line = "";

        try{
            reader = new FileReader("temp.txt"); // FileReader читает только символы и возвращает их код
            bufferedReader = new BufferedReader(reader);

//            readerResult = (char)reader.read(); // вернет букву unicode-код буквы H
//            bufferResult = buffer.readLine(); // вернет только первую строку Hello William,
//            while(reader.read(readerResultArray) != -1){} // заполнит массив символами
            while((line = bufferedReader.readLine()) != null){
                bufferResult.append(line).append("\n");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
//        System.out.println(bufferResult);




        /* WRITER */

        Writer writer;
        BufferedWriter bufferedWriter;

        try{
            writer = new FileWriter("temp2.txt");
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(String.valueOf(bufferResult));
            bufferedWriter.close(); // метод close() автоматически вызывает метод flush()
//            writer.flush();
//            bufferedWriter.write("Здравствуйте, мой друг. Гг343");
//            bufferedWriter.close();



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
