package jdbc.bankingManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner input;

    public AccountManager(Connection connection, Scanner input) {
        this.connection = connection;
        this.input = input;
    }

    public void debitMoney(long account_number) throws SQLException {
        input.nextLine();
        System.out.print("Введите сумму: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.print("Введите PIN-код: ");
        String security_pin = input.nextLine();

        try{
            connection.setAutoCommit(false);
            if(account_number != 0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE account_number = ? AND security_pin = ?");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    double currentBalance = resultSet.getDouble("balance");
                    if(amount <= currentBalance){
                        String debitQuery = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(debitQuery);
                        preparedStatement1.setDouble(1, amount);
                        preparedStatement1.setLong(2, account_number);
                        int row = preparedStatement1.executeUpdate();

                        if(row > 0){
                            System.out.println("РУБЛИ: " + amount + " списано успешно");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Транзакция не удалась");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("Недостаточный баланс");
                    }
                } else {
                    System.out.println("Неверный PIN-код");
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }

    public void creditMoney(long account_number) throws SQLException {
        input.nextLine();
        System.out.print("Введите сумму: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.print("Введите PIN-код: ");
        String security_pin = input.nextLine();

        try{
            connection.setAutoCommit(false);
            if(account_number != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE account_number = ? AND security_pin = ?");
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    String creditQuery = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(creditQuery);
                    preparedStatement1.setDouble(1, amount);
                    preparedStatement1.setLong(2, account_number);
                    int row = preparedStatement1.executeUpdate();

                    if(row > 0){
                        System.out.println("РУБЛИ: " + amount + " зачислено успешно");
                        connection.commit();
                        connection.setAutoCommit(true);
                        return;
                    } else {
                        System.out.println("Транзакция не удалась");
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                } else {
                    System.out.println("Неверный PIN-код");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            connection.setAutoCommit(true);
        }
    }

    public void transferMoney(long sender_account_number) throws SQLException {
        input.nextLine();
        System.out.print("Введите номер получателя: ");
        long receiver_account_number = input.nextLong();
        System.out.print("Введите сумму: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.print("Введите PIN-код: ");
        String security_pin = input.nextLine();

        try{
            connection.setAutoCommit(false);
            if(sender_account_number != 0 && receiver_account_number != 0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE account_number = ? AND security_pin = ?");
                preparedStatement.setLong(1, sender_account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if(amount <= current_balance){
                        String debitQuery = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
                        String creditQuery = "UPDATE account SET balance = balance + ? WHERE account_number = ?";

                        PreparedStatement debitPreparedStatement = connection.prepareStatement(debitQuery);
                        PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery);

                        debitPreparedStatement.setDouble(1, amount);
                        debitPreparedStatement.setLong(2, sender_account_number);
                        creditPreparedStatement.setDouble(1, amount);
                        creditPreparedStatement.setLong(2, receiver_account_number);

                        int row1 = debitPreparedStatement.executeUpdate();
                        int row2 = creditPreparedStatement.executeUpdate();
                        if(row1 > 0 && row2 > 0){
                            System.out.println("Транзакция прошла успешно");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Неверный номер счета или произошел сбой системы");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("Недостаточный баланс");
                    }
                } else {
                    System.out.println("Неверный PIN-код");
                }
            } else {
                System.out.println("Неверный номер счета");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }

    public void getBalance(long account_number){
        input.nextLine();
        System.out.print("Введите PIN-код: ");
        String security_pin = input.nextLine();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM account WHERE account_number = ? AND security_pin = ?");
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2, security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Баланс: " + balance);
            } else {
                System.out.println("Неверный PIN-код");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}










