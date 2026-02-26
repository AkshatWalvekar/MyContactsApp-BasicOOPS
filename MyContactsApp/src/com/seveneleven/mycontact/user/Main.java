package com.seveneleven.mycontact.user;

import java.util.Scanner;
import java.util.*;

import com.seveneleven.mycontact.user.auth.BasicAuth;
import com.seveneleven.mycontact.user.model.*;
import com.seveneleven.mycontact.user.validation.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        //Store Users
        List<User>users = new ArrayList<>();
        
        //UC1: Registration
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

        // Object creation 
        User user;

        if (type.equalsIgnoreCase("free")) {
            user = new FreeUser(username, email, age, password);
        } else {
            user = new PremiumUser(username, email, age, password);
        }
        
        //usercreation
        users.add(user);

        System.out.println("\n Registration Successful!");
        user.display();
        
        //UC2: Login
        System.out.println("Login ");
        
        System.out.println("Enter Username: ");
        String loginUsername = sc.nextLine();
        
        System.out.println("Enter Password: ");
        String loginPassword = sc.nextLine();
        
        BasicAuth auth = new BasicAuth();
        
        User loggedInUser = auth.login(loginUsername, loginPassword, users);
        
        //login status
        if(loggedInUser != null) {
        	System.out.println("Login Successful!");
        	System.out.println("Welcome " + loggedInUser.getUsername());
        }else {
        	System.out.println("Invalid Credentials !");
        }
    }
}