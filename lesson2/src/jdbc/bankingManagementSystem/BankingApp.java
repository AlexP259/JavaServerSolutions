package jdbc.bankingManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Qwerty123!";

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Scanner input = new Scanner(System.in);
            User user = new User(connection, input); // создаем инстанс для регистрации новых пользователей и авторизации
            Accounts accounts = new Accounts(connection, input); // создаем инстанс для создания счета пользователя
            AccountManager accountManager = new AccountManager(connection, input); // создаем инстанс для работы со счетом (снятие, займ, перевод, проверка баланса)

            String email;   // наш email
            long account_number;    // номер нашего счета

            while(true){
                System.out.println("*** ДОБРО ПОЖАЛОВАТЬ В БАНКОВСКУЮ СИСТЕМУ! ***");
                System.out.println("1. Регистрация");
                System.out.println("2. Авторизация");
                System.out.println("3. Выход");

                System.out.print("Ваш выбор: ");
                int choice1 = input.nextInt();
                switch(choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email = user.login();   // авторизация через класс User
                        if(email != null){
                            System.out.println("\nПользователь вошел в систему");
                            if(!accounts.account_exists(email)){ // если счет не существует, то пользователю предлагают открыть его
                                System.out.println("1. Открытие нового банковского счета");
                                System.out.println("2. Выход");
                                System.out.print("Ваш выбор: ");
                                if(input.nextInt() == 1){
                                    account_number = accounts.open_account(email); // открываем счет
                                    System.out.println("Аккаунт создан успешно.\nВаш номер счета: " + account_number + "\n");
                                } else {
                                    System.out.println();
                                    break;
                                }
                            }
                            account_number = accounts.getAccountNumber(email);
                            int choice2 = 0;
                            while(choice2 != 5){    // эти варианты обрабатываются через класс AccountManager
                                System.out.println("\n1. Снятие личных средств");
                                System.out.println("2. Займ кредитных средств");
                                System.out.println("3. Перевод денег");
                                System.out.println("4. Проверка баланса");
                                System.out.println("5. Выход");
                                System.out.print("Ваш выбор: ");
                                choice2 = input.nextInt();
                                switch(choice2){
                                    case 1:
                                        accountManager.debitMoney(account_number);
                                        break;
                                    case 2:
                                        accountManager.creditMoney(account_number);
                                        break;
                                    case 3:
                                        accountManager.transferMoney(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Такого варианта нет");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("\nНекорректный адрес почты или пароль\n");
                        }
                        break;
                    case 3:
                        System.out.println("\nСПАСИБО ЗА ИСПОЛЬЗОВАНИЕ БАНКОВСКОЙ СИСТЕМЫ!");
                        System.out.println("Выход из системы!");
                        input.close();
                        connection.close();
                        return;
                    default:
                        System.out.println("Такого варианта нет");
                        break;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
