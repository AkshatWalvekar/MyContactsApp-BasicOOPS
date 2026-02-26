package com.seveneleven.mycontact.user;

import java.util.Scanner;

import com.seveneleven.mycontact.user.model.*;
import com.seveneleven.mycontact.user.validation.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (!EmailValidator.isValid(email)) {
            System.out.println("Invalid Email!");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!PasswordValidator.isValid(password)) {
            System.out.println("Weak Password!");
            return;
        }

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter user type (free/premium): ");
        String type = sc.nextLine();

        // Object creation (no factory, direct)
        User user;

        if (type.equalsIgnoreCase("free")) {
            user = new FreeUser(username, email, age, password);
        } else {
            user = new PremiumUser(username, email, age, password);
        }

        System.out.println("\n Registration Successful!");
        user.display();
    }
}