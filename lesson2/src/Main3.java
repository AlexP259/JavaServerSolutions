import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class Main3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "Qwerty123!";

//        String imagePath = "E:\\java_projects\\TOP ACADEMY\\Java313\\projects\\OIP.jpg";
//        String query = "INSERT INTO image_table (image_data) " +
//                "VALUES (?)";

        String folderPath = "E:\\java_projects\\TOP ACADEMY\\Java313\\projects\\savedPictures\\";
        String query = "SELECT image_data FROM image_table WHERE image_id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] imageData = resultSet.getBytes("image_data");
                String imagePath =  folderPath + "extractedImage.jpg";
                OutputStream outputStream = new FileOutputStream(imagePath);
                outputStream.write(imageData);
            } else {
                System.out.println("Изображение не найдено");
            }
//            FileInputStream fileInputStream = new FileInputStream(imagePath);
//            byte[] imageData = new byte[fileInputStream.available()];   // метод вернет количество байт которые есть в потоке
//            fileInputStream.read(imageData);
//
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setBytes(1, imageData);
//
//            int row = preparedStatement.executeUpdate();
//            if(row > 0){
//                System.out.println("Изображение добавлено успешно");
//            } else {
//                System.out.println("Ошибка добавления изображения");
//            }
            preparedStatement.close();
//            fileInputStream.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
//        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
