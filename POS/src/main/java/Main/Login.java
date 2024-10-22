package Main;

import Repository.UserRepository;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean run = true;
        while (run) {
            System.out.print("[1]LOGIN\n[2]CREATE\n[3]EXIT\nCHOICE: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Login Details");
                    System.out.println("-----------------");
                    System.out.print("Enter Username: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    User user = new User(userName, password);

                    if (UserRepository.getUserCorrect(user) != null) {
                        System.out.println("LOGIN SUCCESSFULLY");
                        new Services();
                        run = false;
                    } else {
                        System.out.println("Incorrect details");
                    }
                }
                case 2 -> {
                    System.out.println("Signup Details");
                    System.out.println("-----------------");
                    System.out.print("Enter Username: ");
                    String userName = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    User user = new User(userName, password);

                    if (UserRepository.isValid(user)) {
                        System.out.println("Signup successfully");
                    } else {
                        System.out.println("Username duplicate");
                    }
                }
                case 3 -> System.exit(0);

            }
        }

    }
}