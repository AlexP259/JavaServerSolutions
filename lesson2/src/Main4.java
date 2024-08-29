import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main4 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "Qwerty123!";

        String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try{
            Connection connection = DriverManager.getConnection(url, username,password);
            connection.setAutoCommit(false);

            try{
                PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = connection.prepareStatement(depositQuery);

                withdrawStatement.setDouble(1,1500.00);
                withdrawStatement.setString(2,"account1");
                depositStatement.setDouble(1,4500.00);
                depositStatement.setString(2,"account2");

                int rowsWithdraw = withdrawStatement.executeUpdate();
                int rowsDeposit = depositStatement.executeUpdate();

                /* Только если обе операции прошли успешно, выполнится коммит. Так две операции будут выполнены как одна транзакция */
                if(rowsWithdraw > 0 && rowsDeposit > 0){
                    connection.commit();
                    System.out.println("Транзакция успешна");
                } else {
                    connection.rollback();  // откат состояния нашей транзакции до каких-то изменений
                    System.out.println("Транзакция не удалась");
                }
            } catch (SQLException e) {
                System.out.println("Внутренний: " + e.getMessage());
            }
        } catch (SQLException e){
            System.out.println("Внешний: " + e.getMessage());
        }
    }
}
