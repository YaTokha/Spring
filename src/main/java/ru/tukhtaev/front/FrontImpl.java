package ru.tukhtaev.front;

import ru.tukhtaev.services.UsersServices;

import java.util.Scanner;

public class FrontImpl implements Front {

    private UsersServices usersServices;

    public FrontImpl(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("МЕНЮ: ");
            System.out.println("1. Регистрация ");
            System.out.println("2. Авторизация ");
            System.out.println("3. Выход");

            int command = scanner.nextInt();

            switch (command){
                case 1: {
                    System.out.println("Введите имя: ");
                    scanner.nextLine();
                    String firstName = scanner.nextLine();
                    System.out.println("Введите фамилию: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Введите email: ");
                    String email = scanner.nextLine();
                    System.out.println("Введите пароль: ");
                    String password = scanner.nextLine();
                    usersServices.signUp(firstName, lastName, email, password);
                } break;
                case 2: {
                    System.out.println("Введите email: ");
                    String email = scanner.nextLine();
                    System.out.println("Введите пароль: ");
                    String password = scanner.nextLine();
                    usersServices.signIn(email, password);
                } break;
                case 3: {
                    System.exit(0);
                } default:
                    System.err.println("Неверная команда!");
            }
        }
    }
}
